package org.catrawi.atrawica.viewmodels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.HomeViewModel
import org.catrawi.atrawica.viewmodels.repository.HomeRepository

class HomeViewModelFactory constructor(private val application: Application, val repository: HomeRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(this.application,this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}