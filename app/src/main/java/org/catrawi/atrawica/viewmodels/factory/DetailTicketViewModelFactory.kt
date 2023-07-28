package org.catrawi.atrawica.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.DetailTicketViewModel
import org.catrawi.atrawica.viewmodels.HomeViewModel
import org.catrawi.atrawica.viewmodels.repository.DetailTicketRepository

class DetailTicketViewModelFactory  constructor(val repository: DetailTicketRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(DetailTicketViewModel::class.java)) {
            DetailTicketViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}