package com.juaracoding.aplikasiabsensi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juaracoding.aplikasiabsensi.model.ResponseServices
import com.juaracoding.aplikasiabsensi.services.NetworkConfig
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response

class PengajuanCreditViewModel(application: Application) : AndroidViewModel(application) {
    private val _post = MutableLiveData<ResponseServices>()
    val post: LiveData<ResponseServices>
              get() = _post
    lateinit var application: Application


    init{
        this.application = application
    }

    fun postDataCredit(username:String,fotoKK:MultipartBody.Part,fotoKTP:MultipartBody.Part,fotoNPWP:MultipartBody.Part,tanggal:String){
        NetworkConfig().getServicePengajuanCredit().addPengajuanCredit(username,fotoKK,fotoKTP,fotoNPWP,tanggal).enqueue(object : retrofit2.Callback<ResponseServices>{
            override fun onResponse(p0: Call<ResponseServices>, p1: Response<ResponseServices>) {
               _post.postValue(p1.body())
            }

            override fun onFailure(p0: Call<ResponseServices>, p1: Throwable) {
                Toast.makeText(application.applicationContext,"Gagal Mengupload Data Credit",Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getSharePreferencesLogin(key:String):String{
        return application.getSharedPreferences("LOGIN",0).getString(key,"").toString()

    }

}