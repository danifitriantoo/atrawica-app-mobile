package org.catrawi.atrawica.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.catrawi.atrawica.adapters.PlaceAdapter
import org.catrawi.atrawica.adapters.interfaces.ItemListener
import org.catrawi.atrawica.databinding.FragmentEasyBinding
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.HomeViewModel
import org.catrawi.atrawica.viewmodels.factory.HomeViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.HomeRepository
import org.catrawi.atrawica.views.DetailActivity


class EasyFragment : Fragment(), ItemListener {

    private lateinit var homeViewModel: HomeViewModel
    private val apiService = ApiService.getService()
    private lateinit var binding: FragmentEasyBinding
    private val adapter = PlaceAdapter()
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentEasyBinding.inflate(inflater,container,false)

        shimmerFrameLayout = binding.placeShimmerLayout
        shimmerFrameLayout.startShimmer()

        homeViewModel = ViewModelProvider(requireActivity(),
            HomeViewModelFactory(HomeRepository(apiService)))[HomeViewModel::class.java]

        //set recyclerview adapter
        val layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvPlaces.layoutManager = layoutManager
        binding.rvPlaces.adapter = adapter

        adapter.listener = this
//
        return binding.root

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        GlobalScope.launch(Dispatchers.IO) {
            val result = async { homeViewModel.getAllPlace(
                SessionManager.getToken(requireActivity()).toString()
            ) }

            result.await()

        }

        homeViewModel.responseData.observe(requireActivity()) {
            adapter.setPlaceList(it)
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
        }
    }

    override fun onItemClicked(view: View, data: Place) {
        val intent = Intent(
            this@EasyFragment.requireContext(),
            DetailActivity::class.java)
            .putExtra("id",data.id)
            .putExtra("name",data.name)
            .putExtra("description",data.description)
            .putExtra("price",data.price)

        startActivity(intent)
    }
}