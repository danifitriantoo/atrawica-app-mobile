package org.catrawi.atrawica.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.AuthViewModel
import org.catrawi.atrawica.viewmodels.repository.AuthRepository

class AuthViewModelFactory constructor(private val repository: AuthRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            AuthViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}