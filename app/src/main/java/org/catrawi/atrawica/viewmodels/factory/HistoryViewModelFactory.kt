package org.catrawi.atrawica.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.HistoryViewModel
import org.catrawi.atrawica.viewmodels.HomeViewModel
import org.catrawi.atrawica.viewmodels.repository.HistoryRepository
import org.catrawi.atrawica.viewmodels.repository.HomeRepository

class HistoryViewModelFactory constructor(private val repository: HistoryRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            HistoryViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}