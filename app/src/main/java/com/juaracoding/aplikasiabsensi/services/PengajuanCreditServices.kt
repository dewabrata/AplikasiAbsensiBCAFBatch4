package com.juaracoding.aplikasiabsensi.services

import com.juaracoding.aplikasiabsensi.model.ResponseCredit
import com.juaracoding.aplikasiabsensi.model.ResponseServices
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PengajuanCreditServices {

    @Multipart
    @POST("pengajuan_credit/add")
    fun addPengajuanCredit (@Part("username") username:RequestBody,
                            @Part fotoKK : MultipartBody.Part,
                            @Part fotoKTP : MultipartBody.Part,
                            @Part fotoNPWP : MultipartBody.Part,
                            @Part("tanggal") tanggal : RequestBody,
                            ): Call<ResponseServices>


    @GET("pengajuan_credit/all")
    fun getAllPengajuanCredit(): Call<ResponseCredit>
}