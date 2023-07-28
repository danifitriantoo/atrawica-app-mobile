package org.catrawi.atrawica.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.catrawi.atrawica.R
import org.catrawi.atrawica.adapters.FavoriteAdapter
import org.catrawi.atrawica.adapters.PlaceAdapter
import org.catrawi.atrawica.adapters.interfaces.FavListener
import org.catrawi.atrawica.databinding.FragmentEasyBinding
import org.catrawi.atrawica.databinding.FragmentFavoriteBinding
import org.catrawi.atrawica.models.Favorite
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.room.FavoritePlace
import org.catrawi.atrawica.room.FavoritePlaceDao
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.viewmodels.HomeViewModel
import org.catrawi.atrawica.viewmodels.factory.HomeViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.HomeRepository
import org.catrawi.atrawica.views.DetailActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : Fragment(), FavListener {

    private lateinit var viewModel: HomeViewModel
    private val adapter = FavoriteAdapter()
    private val apiService = ApiService.getService()
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var dao: FavoritePlaceDao
    private var favorites = mutableListOf<Place>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPlaces(1)

        viewModel.getData.observe(requireActivity()) {
            adapter.setPlaceList(it)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(requireActivity(),
            HomeViewModelFactory(
                requireActivity().application,
                HomeRepository(apiService)
            )
        ).get(HomeViewModel::class.java)

        // Inflate the layout for this fragment
        val layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvFavorite.layoutManager = layoutManager
        binding.rvFavorite.adapter = adapter

        adapter.listener = this

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onFavoriteClicked(view: View, data: FavoritePlace) {
        Log.d("is Clicked","I am!")

        val intent = Intent(
            this@FavoriteFragment.requireContext(),
            DetailActivity::class.java)
            .putExtra("id", data.id)
            .putExtra("name", data.name)
            .putExtra("description", data.description)
            .putExtra("price", data.price)
            .putExtra("image", data.image)


        startActivity(intent)
    }
}