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
import org.catrawi.atrawica.R
import org.catrawi.atrawica.adapters.HistoryAdapter
import org.catrawi.atrawica.adapters.interfaces.TicketListener
import org.catrawi.atrawica.databinding.FragmentMyTicketBinding
import org.catrawi.atrawica.models.BookingResponse
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.HistoryViewModel
import org.catrawi.atrawica.viewmodels.factory.HistoryViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.HistoryRepository
import org.catrawi.atrawica.views.TicketActivity
import java.lang.Exception


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

        if(isAdded) {
            viewModel.responseBooking.observe(requireActivity()) {

                if(it.isEmpty()) {
                    binding.mainScrollView.visibility = View.GONE

                } else {
                    binding.mainScrollView.visibility = View.VISIBLE
                    adapter.setHistoryList(it)

                    viewModel.getDetailBooking(
                        SessionManager.getToken(requireActivity()).toString(),
                        it[0].id!!.toInt())

                    binding.placeNameTextView.text = it[0].place.name
                    binding.personTextView.text = "${it[0].qty} Person"
                    binding.dateTextView.text = it[0].date
                }

            }

            viewModel.responseData.observe(requireActivity()) {
                Log.d("Detail booking data ", it.toString())

                if(it.isEmpty()) {

                }else {
                    binding.waktuBerangkatTextView.text = it[0].departureTime
                    binding.waktuPulangTextView.text = it[0].backTime

                    if(it[0].status) {
                        binding.waktuBerangkatSuccessImageView.visibility = View.VISIBLE
                        binding.waktuPulangSuccessImageView.visibility = View.VISIBLE
                    }
                }

            }

            viewModel.errorLog.observe(requireActivity()) {
                Log.d("Error ", "errorMessage: $it")
            }

            viewModel.getAllBooking(
                SessionManager.getToken(requireContext()).toString(),SessionManager.getPayload(requireActivity()).id)
        }

        var isActive : Boolean = false

        binding.arrowImageButton.setOnClickListener {

            if(isActive) {
                binding.detailTicketLinearLayout.visibility = View.GONE
                binding.arrowImageButton.setImageResource(R.drawable.ic_arrow_down)
                isActive = true
            }

            if(!isActive) {
                binding.detailTicketLinearLayout.visibility = View.VISIBLE
                binding.arrowImageButton.setImageResource(R.drawable.ic_arrow_up)
                isActive = false
            }

        }

        binding.ticketCodeImageView.setOnClickListener {
            val intent = Intent(
                this@MyTicketFragment.requireContext(),
                TicketActivity::class.java)
            startActivity(intent)
        }

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