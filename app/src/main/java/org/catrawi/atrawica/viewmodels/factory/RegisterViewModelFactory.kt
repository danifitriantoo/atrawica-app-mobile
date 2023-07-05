package org.catrawi.atrawica.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.AuthViewModel
import org.catrawi.atrawica.viewmodels.RegisterViewModel
import org.catrawi.atrawica.viewmodels.repository.AuthRepository
import org.catrawi.atrawica.viewmodels.repository.RegisterRepository

class RegisterViewModelFactory constructor(private val repository: RegisterRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            RegisterViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}