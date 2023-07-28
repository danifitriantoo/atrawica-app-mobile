package org.catrawi.atrawica.views.fragments.Tickets

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import org.catrawi.atrawica.R
import org.catrawi.atrawica.databinding.FragmentBackBinding
import org.catrawi.atrawica.databinding.FragmentDepartBinding
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.SessionManager
import org.catrawi.atrawica.viewmodels.DetailTicketViewModel
import org.catrawi.atrawica.viewmodels.factory.DetailTicketViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.DetailTicketRepository

class BackFragment : Fragment() {

    private lateinit var viewModel: DetailTicketViewModel
    private lateinit var binding: FragmentBackBinding
    private val apiService = ApiService.getService()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBackBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(
            this, DetailTicketViewModelFactory(
                DetailTicketRepository(apiService)
            )
        )[DetailTicketViewModel::class.java]

//        viewModel.getDetailBooking(
//            SessionManager.getToken(requireActivity())!!,SessionManager.getBookingId(requireActivity(),"BookingId"))

        viewModel.getDetailBooking(SessionManager.getToken(requireActivity())!!,16)


        viewModel.bookingTicketResponse.observe(requireActivity()) {
            val content = it[0].id.toString()

            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            binding.ivCode.setImageBitmap(bitmap)

            viewModel.getTicket(SessionManager.getToken(requireActivity())!!,it[0].ticket.place,it[0].ticket.departure)

        }

        viewModel.detailTicketResponse.observe(requireActivity()) {
            binding.departTerminalTextView.text = it[0].departure.terminal
            binding.transitTerminalTextView.text = it[0].transit.name
            binding.placeTextView.text = it[0].place.name
        }

        viewModel.errorLog.observe(requireActivity()) {
            Log.d("Error at Ticket",it.toString())
        }


        return binding.root
    }
}