package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.models.Booking
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.ApiService.Companion.apiService

class BookingRepository constructor(private val apiService: ApiService) {
    fun postBooking(
        token: String,
        data: Booking)
    = apiService.postBooking(token,data)
    fun getAllBooking(
        token: String,
        userId: Int)
    = apiService.getAllBooking(token,userId)

    /* buat untuk getBooking dan putBooking */
}