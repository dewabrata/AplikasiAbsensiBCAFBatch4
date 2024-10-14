package com.juaracoding.aplikasiabsensi.services

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       val request = chain.request().newBuilder()
           .addHeader("X-Api-Key","67FC26AEA770E3ED25A1F9B1EBCB7408")
           .build()
        return chain.proceed(request)
    }
}