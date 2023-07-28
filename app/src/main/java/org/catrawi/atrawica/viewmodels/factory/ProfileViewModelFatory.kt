package org.catrawi.atrawica.viewmodels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.HomeViewModel
import org.catrawi.atrawica.viewmodels.ProfileViewModel
import org.catrawi.atrawica.viewmodels.repository.HomeRepository
import org.catrawi.atrawica.viewmodels.repository.ProfileRespository

class ProfileViewModelFatory  constructor(val repository: ProfileRespository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            ProfileViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}