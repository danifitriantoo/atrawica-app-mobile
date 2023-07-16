package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.services.api.ApiService

class HistoryRepository constructor(private val apiService: ApiService) {
    fun getAllBooking(
        token: String,
        userId: Int)
    = apiService.getAllBooking(token,userId)
    fun getDetailBooking(
        token: String,
        bookingId: Int)
    = apiService.getDetailBooking(token,bookingId)
}