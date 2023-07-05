package org.catrawi.atrawica.views

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.databinding.ActivityBookingBinding
import org.catrawi.atrawica.models.Booking
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.viewmodels.BookingViewModel
import org.catrawi.atrawica.viewmodels.factory.BookingViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.BookingRepository
import java.util.Calendar
import java.util.Locale


class BookingActivity : AppCompatActivity() {

    private lateinit var viewModel: BookingViewModel
    private val apiService = ApiService.getService()
    private var calendar : Calendar = Calendar.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,BookingViewModelFactory(BookingRepository(apiService)))
            .get(BookingViewModel::class.java)

        val placeId = intent.getIntExtra("id",0)

        Log.d("User Id",placeId.toString())


        val date =
            OnDateSetListener { view, year, month, day ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)

                binding.etDate.setText(SimpleDateFormat(
                    "yyyy-MM-dd", Locale.US)
                    .format(calendar.getTime()));
            }

        binding.etDate.setOnClickListener {
            DatePickerDialog(
                this,
                date,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnBayar.setOnClickListener {
            val date = binding.etDate.text.toString()

            val data = Booking(0, 18, 1602, "2023-12-12", 1)

            viewModel.postBooking(data)

            val intent = Intent(
                this,
                BookingTicketActivity::class.java)

            viewModel.responseData.observe(this,Observer {

            })

            startActivity(intent)
        }
    }
}