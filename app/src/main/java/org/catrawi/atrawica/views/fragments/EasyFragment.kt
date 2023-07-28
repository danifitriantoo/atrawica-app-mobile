package org.catrawi.atrawica.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.view.ContentInfoCompat.Flags
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.catrawi.atrawica.adapters.PlaceAdapter
import org.catrawi.atrawica.adapters.interfaces.FavoriteListener
import org.catrawi.atrawica.adapters.interfaces.ItemListener
import org.catrawi.atrawica.databinding.DialogLoadingBinding
import org.catrawi.atrawica.databinding.FragmentEasyBinding
import org.catrawi.atrawica.databinding.ModalSheetBinding
import org.catrawi.atrawica.models.Place
import org.catrawi.atrawica.room.FavoritePlaceDao
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.AuthViewModel
import org.catrawi.atrawica.viewmodels.HomeViewModel
import org.catrawi.atrawica.viewmodels.factory.AuthViewModelFactory
import org.catrawi.atrawica.viewmodels.factory.HomeViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.AuthRepository
import org.catrawi.atrawica.viewmodels.repository.HomeRepository
import org.catrawi.atrawica.views.DetailActivity
import org.catrawi.atrawica.views.LoginActivity

class EasyFragment : Fragment(), ItemListener, FavoriteListener {

    private lateinit var viewModel: HomeViewModel
    private val apiService = ApiService.getService()
    private lateinit var binding: FragmentEasyBinding
    private val adapter = PlaceAdapter()
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var modalSheetBinding: ModalSheetBinding
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentEasyBinding.inflate(inflater,container,false)

        modalSheetBinding = ModalSheetBinding.inflate(layoutInflater)
        bottomSheetDialog = BottomSheetDialog(requireContext())
        shimmerFrameLayout = binding.placeShimmerLayout
        shimmerFrameLayout.startShimmer()

        viewModel = ViewModelProvider(requireActivity(),
            HomeViewModelFactory(
                requireActivity().application,
                HomeRepository(apiService)
            )).get(HomeViewModel::class.java)

        //set recyclerview adapter
        val layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvPlaces.layoutManager = layoutManager
        binding.rvPlaces.adapter = adapter

        adapter.listener = this
        adapter.toggleListener = this

        return binding.root


    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        GlobalScope.launch(Dispatchers.IO) {
            val result = async { viewModel.getAllPlace(
                SessionManager.getToken(requireActivity()).toString()
            ) }

            result.await()

        }

        viewModel.errorLog.observe(requireActivity()) {
            if(it == "Unauthorized") { showModalSheet() }
        }

        viewModel.responseData.observe(requireActivity()) {
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
            .putExtra("image",data.image)

        Log.d("Price is a ", data.price.toString())

        SessionManager.saveTicketPrice(requireActivity(),"TicketPrice",data.price)

        startActivity(intent)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onToggleChanged(view: CompoundButton, data: Place) {
        GlobalScope.launch(Dispatchers.IO) {
            val result = async {
                viewModel.addToFavorite(data)
                viewModel.checkFavorite(data.id)
            }

            result.await()

        }
    }

    private fun showModalSheet() {
        bottomSheetDialog.setContentView(modalSheetBinding.root)

        modalSheetBinding.btnConfirm.setOnClickListener {
            SessionManager.clearData(requireContext())
            val intent = Intent(
                this@EasyFragment.requireContext(),
                LoginActivity::class.java)

            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)
            bottomSheetDialog.dismiss()
        }

        modalSheetBinding.tvTitle.text = "Session Kamu Berakhir"
        modalSheetBinding.tvContent.text = "Woops, session kamu berakhir silahkan login lagi buat lanjutin booking wisatanya"
        bottomSheetDialog.setCancelable(false)

        bottomSheetDialog.show()
    }


}