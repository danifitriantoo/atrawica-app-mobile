package org.catrawi.atrawica.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.databinding.ActivityBookingTicketBinding
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.viewmodels.DetailBookingViewModel
import org.catrawi.atrawica.viewmodels.factory.DetailBookingViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.DetailBookingRepository

class BookingTicketActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailBookingViewModel
    private val apiService = ApiService.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookingTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, DetailBookingViewModelFactory(DetailBookingRepository(apiService)))
            .get(DetailBookingViewModel::class.java)

        viewModel.getTicket(1602,40281)
        viewModel.responseTicket.observe(this , Observer {
            Log.d("Data Ticket",it.toString())

            binding.tvTerminal.setText(it[0].departure.terminal)
            binding.tvHalte.setText(it[0].transit.name)
            binding.tvTemple.setText(it[0].place.name)
        })

        Log.d("Data Booking onCreate",intent.getIntExtra("bookingId",0).toString());
    }


    override fun onResume() {
        super.onResume()
        Log.d("Data Booking",intent.getIntExtra("bookingId",0).toString());
    }
}