package org.catrawi.atrawica.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.catrawi.atrawica.R
import org.catrawi.atrawica.databinding.FragmentEasyBinding
import org.catrawi.atrawica.databinding.FragmentHomeBinding
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.views.DetailActivity
import org.catrawi.atrawica.views.ProfileActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)

        if(isAdded) {
            val budget = SessionManager.getPayloadData(requireActivity()).budget
            binding.tvMyBudget.text = "IDR " + budget.toString()
        }

        binding.profileImageButton.setOnClickListener {
            val intent = Intent(
                this@HomeFragment.requireContext(),
                ProfileActivity::class.java)

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

            startActivity(intent)
        }


        return binding.root
    }

}