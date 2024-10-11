package com.juaracoding.aplikasiabsensi.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://b2a2-140-213-4-209.ngrok-free.app/cicool/api/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getServiceReimburstment() = getRetrofit().create(ReimburstmentServices::class.java)

}