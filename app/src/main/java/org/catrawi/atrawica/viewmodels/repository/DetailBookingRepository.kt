package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.models.DetailBooking
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.ApiService.Companion.apiService
import retrofit2.http.Query

class DetailBookingRepository constructor(private val apiService: ApiService) {
    fun postDetailBooking(token: String,data: DetailBooking) = apiService.postDetailBooking(token,data)

    fun getDetailBooking(token: String) = apiService.getDetailBooking(token,0)
    fun getTicket(token: String,placeId : Int,departureId : Int) = apiService.getTicket(token,placeId,departureId)

    fun putDetailBooking(token: String) = apiService.putDetailBooking(token,0)
}