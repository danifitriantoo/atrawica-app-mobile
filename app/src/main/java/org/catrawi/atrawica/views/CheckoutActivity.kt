package org.catrawi.atrawica.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this, DetailBookingViewModelFactory(
                DetailBookingRepository(apiService)
            ))[DetailBookingViewModel::class.java]

        val bookingId = SessionManager.getBookingId(this,"BookingId")
        val price = intent.getIntExtra("price",0)
        val totalprice = (price * 0.50) + (price * 0.30) + (price * 0.025) + (price * 0.02)

        val data = DetailBooking(
            0,bookingId,
            SessionManager.getTicketId(this,"TicketId"),0,
            "08:30","15:00",false,
            totalprice.toInt())

        binding.tvTransport.text = "IDR " + (price * 0.50).toInt().toString()
        binding.tvTicket.text = "IDR " + (price * 0.30).toInt().toString()
        binding.tvPpn.text = "IDR " + (price * 0.025).toInt().toString()
        binding.tvService.text = "IDR " + (price * 0.02).toInt().toString()

        if(intent.getIntExtra("PayId",0) == 1) {
            binding.paymentButton.text = "Gopay"
        } else if(intent.getIntExtra("PayId",0) == 2) {
            binding.paymentButton.text = "OVO"
        } else if(intent.getIntExtra("PayId",0) == 3) {
            binding.paymentButton.text = "ATM"
        } else {
            binding.paymentButton.text = "Pilih metode pembayaran"
        }

        binding.tvTotalPrice.text = "IDR " + (
                (price * 0.50) + (price * 0.30) + (price * 0.025) + (price * 0.02)).toInt().toString()

        binding.btnBayar.setOnClickListener {

            try {
                viewModel.postDetailBooking(SessionManager.getToken(this).toString(),data)
            } finally {
                val intent = Intent(this@CheckoutActivity, ConfirmationActivity::class.java)
                startActivity(intent)
            }
        }

        binding.paymentButton.setOnClickListener {
            val intent = Intent(this@CheckoutActivity, PaymentActivity::class.java)
            startActivity(intent)
        }
    }
}