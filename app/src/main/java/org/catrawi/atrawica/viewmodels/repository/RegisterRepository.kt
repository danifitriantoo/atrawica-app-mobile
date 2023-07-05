package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.models.User
import org.catrawi.atrawica.services.api.ApiService

class RegisterRepository constructor(private val apiService: ApiService) {

    fun register(data: User) =
        apiService.register(data)

}