package org.catrawi.atrawica.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.BookingViewModel
import org.catrawi.atrawica.viewmodels.DetailBookingViewModel
import org.catrawi.atrawica.viewmodels.repository.DetailBookingRepository

class DetailBookingViewModelFactory(
    val repository: DetailBookingRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailBookingViewModel::class.java)) {
            DetailBookingViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}