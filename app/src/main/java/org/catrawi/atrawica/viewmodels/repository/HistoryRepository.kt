package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.services.api.ApiService

class HistoryRepository constructor(private val apiService: ApiService) {
    fun getAllBooking(userId: Int) = apiService.getAllBooking(userId)
    fun getDetailBooking(bookingId: Int) = apiService.getDetailBooking(bookingId)
}