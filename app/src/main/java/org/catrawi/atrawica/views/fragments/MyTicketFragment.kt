package org.catrawi.atrawica.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.catrawi.atrawica.adapters.HistoryAdapter
import org.catrawi.atrawica.adapters.PlaceAdapter
import org.catrawi.atrawica.adapters.interfaces.TicketListener
import org.catrawi.atrawica.databinding.FragmentEasyBinding
import org.catrawi.atrawica.databinding.FragmentMyTicketBinding
import org.catrawi.atrawica.models.BookingResponse
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.viewmodels.HistoryViewModel
import org.catrawi.atrawica.viewmodels.HomeViewModel
import org.catrawi.atrawica.viewmodels.factory.HistoryViewModelFactory
import org.catrawi.atrawica.viewmodels.factory.HomeViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.HistoryRepository
import org.catrawi.atrawica.viewmodels.repository.HomeRepository
import org.catrawi.atrawica.views.DetailActivity
import org.catrawi.atrawica.views.TicketActivity

class MyTicketFragment : Fragment(), TicketListener {
    private lateinit var viewModel: HistoryViewModel
    private val apiService = ApiService.getService()
    private lateinit var binding: FragmentMyTicketBinding
    private val adapter = HistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMyTicketBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(requireActivity(),
            HistoryViewModelFactory(HistoryRepository(apiService))
        )[HistoryViewModel::class.java]

        //set recyclerview adapter
        val layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvTickets.layoutManager = layoutManager
        binding.rvTickets.adapter = adapter

        adapter.listener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.responseBooking.observe(requireActivity()) {
            Log.d("API Call", "places : $it")
            adapter.setHistoryList(it)
        }

        viewModel.errorLog.observe(requireActivity()) {
            Log.d("Error ", "errorMessage: $it")
        }

        viewModel.getAllBooking(18)

    }

    override fun onTicketClicked(view: View, data: BookingResponse) {
        val intent = Intent(
            this@MyTicketFragment.requireContext(),
            TicketActivity::class.java)
            .putExtra("id",data.id)
            .putExtra("name",data.place.name)


        startActivity(intent)
    }
}