package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.models.DetailBooking
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.ApiService.Companion.apiService
import retrofit2.http.Query

class DetailBookingRepository constructor(private val apiService: ApiService) {
    fun postDetailBooking(data: DetailBooking) = apiService.postDetailBooking(data)

    fun getDetailBooking() = apiService.getDetailBooking(0)
    fun getTicket(placeId : Int,departureId : Int) = apiService.getTicket(placeId,departureId)

    fun putDetailBooking() = apiService.putDetailBooking(0)
}