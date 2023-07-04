package org.catrawi.atrawica.viewmodels.repository

import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.services.api.ApiService

class AuthRepository constructor(private val apiService: ApiService) {

    fun userAuth(data: Credential) =
        apiService.login(
        email = data.email,
        keypass = data.keypass)

}