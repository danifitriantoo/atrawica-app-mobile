package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.models.Booking
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.services.api.ApiService.Companion.apiService

class BookingRepository constructor(private val apiService: ApiService) {
    fun postBooking(data: Booking) = apiService.postBooking(data)
    fun getAllBooking(userId: Int) = apiService.getAllBooking(userId)

    /* buat untuk getBooking dan putBooking */
}