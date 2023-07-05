package org.catrawi.atrawica.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.BookingViewModel
import org.catrawi.atrawica.viewmodels.TicketViewModel
import org.catrawi.atrawica.viewmodels.repository.BookingRepository
import org.catrawi.atrawica.viewmodels.repository.TicketRepository

class TicketViewModelFactory constructor(private val repository: TicketRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(TicketViewModel::class.java)) {
            TicketViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }

}