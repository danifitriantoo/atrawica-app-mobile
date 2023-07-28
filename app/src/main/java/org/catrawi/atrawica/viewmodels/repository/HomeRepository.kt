package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.room.FavoritePlaceDao
import org.catrawi.atrawica.services.api.ApiService

class HomeRepository (private val apiService: ApiService) {
    fun getAllPlace(token:String) = apiService.getAllPlace(token)
}