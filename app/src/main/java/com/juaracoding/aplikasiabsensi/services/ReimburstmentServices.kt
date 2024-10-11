package com.juaracoding.aplikasiabsensi.services

import com.juaracoding.aplikasiabsensi.model.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ReimburstmentServices {

    @Multipart
    @POST("/reimbursment/add")
    suspend fun addDocument(@Header("X-Api-Key") apiKey: String,
                            @Part("username") username:RequestBody,
                            @Part("tanggal") tanggal:RequestBody,
                            @Part("fotosurat") fotosurat: MultipartBody.Part
                            ):retrofit2.Response<Response>
}