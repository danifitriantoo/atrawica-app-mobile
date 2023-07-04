package org.catrawi.atrawica

import androidx.lifecycle.ViewModelProvider
import org.catrawi.atrawica.models.Credential
import org.catrawi.atrawica.services.api.ApiService
import org.catrawi.atrawica.viewmodels.TestViewModel
import org.catrawi.atrawica.viewmodels.repository.TestRepository
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.manipulation.Ordering

class ApiUnitTest {
    private lateinit var testViewModel: TestViewModel
    private val apiService = ApiService.getService()
    lateinit var testContext: Ordering.Context

    @Before
    fun setup() {

//        testContext = InstrumentationRegistry.getInstrumentation().context
//
//        testViewModel = ViewModelProvider(requireActivity(),
//            TestViewModel(TestRepository(apiService)))[TestViewModel::class.java]
    }

    @Test
    fun register() {
        Assert.assertEquals(200, testViewModel.getAllPlace())
    }

    @Test
    fun authentication() {
        var data = Credential(
            "d@mail.com",
            "danifn12"
        )
        Assert.assertEquals(200, testViewModel.userAuth(data))
    }

    @Test
    fun getListPlace() {
        Assert.assertEquals(200, testViewModel.getAllPlace())
    }

    @Test
    fun getSinglePlace() {
        testViewModel.getAllPlace()
    }


}