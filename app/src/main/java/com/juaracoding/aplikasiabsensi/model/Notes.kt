package com.juaracoding.aplikasiabsensi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notes(
    val judul: String,
    val isi: String,
):Parcelable
