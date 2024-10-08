package com.juaracoding.aplikasiabsensi

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Absensi(
    val nama: String,
    val lokasi: String,
    val jenisAbsen: String,
    val tanggalAbsen: String,
    val shiftKerja: String,
    val foto: Bitmap
): Parcelable
