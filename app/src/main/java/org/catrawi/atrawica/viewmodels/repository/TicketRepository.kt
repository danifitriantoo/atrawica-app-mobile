package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.services.api.ApiService

class TicketRepository constructor(private val apiService: ApiService) {
    fun getTicket(token: String,placeId : Int,departureId : Int) = apiService.getTicket(token,placeId,departureId)

}