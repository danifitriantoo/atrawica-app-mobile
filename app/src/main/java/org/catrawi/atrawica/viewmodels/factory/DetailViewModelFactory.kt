package org.catrawi.atrawica.viewmodels.factory

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.repository.HomeRepository

class DetailViewModelFactory constructor(private val application: Application, val repository: HomeRepository) :
    ViewModelProvider.Factory {

}