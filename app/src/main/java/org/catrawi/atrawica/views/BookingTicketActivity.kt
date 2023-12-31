package org.catrawi.atrawica.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.databinding.ActivityBookingTicketBinding
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.TicketViewModel
import org.catrawi.atrawica.viewmodels.factory.TicketViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.TicketRepository

class BookingTicketActivity : AppCompatActivity() {

    private lateinit var viewModel: TicketViewModel
    private val apiService = ApiService.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookingTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this, TicketViewModelFactory(TicketRepository(apiService)
            ))[TicketViewModel::class.java]

        viewModel.getTicket(SessionManager.getToken(this).toString(),3,40281)
        viewModel.responseData.observe(this) {

            SessionManager.saveTicketId(this,"TicketId",it[0].id)
            SessionManager.saveTicketPrice(this,"TicketPrice",it[0].price)
            binding.tvTerminal.text = it[0].departure.terminal
            binding.tvHalte.text = it[0].transit.name
            binding.tvTemple.text = it[0].place.name
        }

        binding.btnBayar.setOnClickListener {
            val price = intent.getIntExtra("price",0)
            val intent = Intent(this,CheckoutActivity::class.java)
            intent.putExtra("price",price)
            startActivity(intent)
        }

    }


    override fun onResume() {
        super.onResume()
    }
}