package org.catrawi.atrawica.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.databinding.ActivityCheckoutBinding
import org.catrawi.atrawica.models.DetailBooking
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.DetailBookingViewModel
import org.catrawi.atrawica.viewmodels.factory.DetailBookingViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.DetailBookingRepository

class CheckoutActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailBookingViewModel
    private val apiService = ApiService.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this, DetailBookingViewModelFactory(
                DetailBookingRepository(apiService)
            ))[DetailBookingViewModel::class.java]

        val data = DetailBooking(
            0,2078,2234,0,"13:20","17:50",false,300000)

        binding.btnBayar.setOnClickListener {
            try {
                viewModel.postDetailBooking(SessionManager.getToken(this).toString(),data)
            } finally {
                val intent = Intent(this@CheckoutActivity, ConfirmationActivity::class.java)
                startActivity(intent)
            }
        }
    }
}