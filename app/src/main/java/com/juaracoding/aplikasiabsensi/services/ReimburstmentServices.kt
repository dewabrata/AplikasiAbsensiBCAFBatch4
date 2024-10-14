package com.juaracoding.aplikasiabsensi.services

import com.juaracoding.aplikasiabsensi.model.ResponseServices
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ReimburstmentServices {

    @Multipart
    @POST("reimbursment/add")
     fun addDocument(
                            @Part("username") username:RequestBody,
                            @Part("tanggal") tanggal:RequestBody,
                            @Part fotosurat: MultipartBody.Part
                            ): Call<ResponseServices>
}