package org.catrawi.atrawica

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.viewmodels.TestViewModel
import org.catrawi.atrawica.viewmodels.factory.TestViewModelFactory
import org.catrawi.atrawica.viewmodels.repository.TestRepository
import org.junit.Before

class ApiConsumeTest {
    private lateinit var testViewModel: TestViewModel
    private val apiService = ApiService.getService()

//    @Before
//    fun setup() {
//        val testContext = InstrumentationRegistry.getInstrumentation()
//
//        testViewModel = ViewModelProvider(testContext.context,
//            TestViewModelFactory(TestRepository(apiService)))[TestViewModel::class.java]
//    }

}