package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.models.User
import org.catrawi.atrawica.services.api.ApiService

class ProfileRespository (private val apiService: ApiService) {
    fun updateBudget(token:String, user: User) = apiService.updateBudget(token,user)
}