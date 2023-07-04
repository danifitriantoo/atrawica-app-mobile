package org.catrawi.atrawica.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.viewmodels.TestViewModel
import org.catrawi.atrawica.viewmodels.repository.TestRepository

class TestViewModelFactory constructor(private val repository: TestRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(TestViewModel::class.java)) {
            TestViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}