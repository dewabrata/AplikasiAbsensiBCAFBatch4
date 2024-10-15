package com.juaracoding.aplikasiabsensi.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseCredit(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("pengajuan_credit")
	val pengajuanCredit: List<PengajuanCreditItem?>? = null
) : Parcelable

@Parcelize
data class PengajuanCreditItem(

	@field:SerializedName("foto_kk")
	val fotoKk: String? = null,

	@field:SerializedName("foto_npwp")
	val fotoNpwp: String? = null,

	@field:SerializedName("foto_ktp")
	val fotoKtp: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("username")
	val username: String? = null
) : Parcelable
