package org.catrawi.atrawica.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.BookingViewModel
import org.catrawi.atrawica.viewmodels.repository.BookingRepository
import org.catrawi.atrawica.viewmodels.repository.HomeRepository

class BookingViewModelFactory constructor(private val repository: BookingRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(BookingViewModel::class.java)) {
            BookingViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }

}