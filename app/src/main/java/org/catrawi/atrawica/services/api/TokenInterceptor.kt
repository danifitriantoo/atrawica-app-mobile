package org.catrawi.atrawica.services.api

import android.app.Application
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class TokenInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .header("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjE4IiwidW5pcXVlX25hbWUiOiJkYW5pIiwibm9faHAiOiJzdHJpbmciLCJidWRnZXQiOiIwIiwiZW1haWwiOiJmbkBtYWlsLmNvbSIsIm5iZiI6MTY4OTEzMDAxNiwiZXhwIjoxNjg5MTMzNjE2LCJpYXQiOjE2ODkxMzAwMTZ9.b8YxcRTSR4aOznT68avQoKK6N4mzTURXOEJw2MYlnW0")
            .build()



        return chain.proceed(request)
    }

}