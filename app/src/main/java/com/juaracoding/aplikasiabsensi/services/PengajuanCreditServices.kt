package com.juaracoding.aplikasiabsensi.services

import com.juaracoding.aplikasiabsensi.model.ResponseServices
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PengajuanCreditServices {

    @Multipart
    @POST
    fun addPengajuanCredit (@Part("username") username:String,
                            @Part("foto_kk") fotoKK : MultipartBody.Part,
                            @Part("foto_ktp") fotoKTP : MultipartBody.Part,
                            @Part("foto_npwp") fotoNPWP : MultipartBody.Part,
                            @Part("tanggal") tanggal : String,
                            ): Call<ResponseServices>
}