package org.catrawi.atrawica.viewmodels.repository

import android.util.Log
import org.catrawi.atrawica.models.DetailBookingMeta
import org.catrawi.atrawica.services.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTicketRepository(private val apiService: ApiService) {

    fun getDetailTicket(token: String,bookingId: Int) =
        apiService.getDetailBookingTicket(token,bookingId)

    fun getTicket(token: String,placeId : Int,departureId : Int) =
        apiService.getTicket(token,placeId,departureId)

}