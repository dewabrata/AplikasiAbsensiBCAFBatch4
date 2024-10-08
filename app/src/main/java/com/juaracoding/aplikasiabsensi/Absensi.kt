package com.juaracoding.aplikasiabsensi

import android.graphics.Bitmap

data class Absensi(
    val nama: String,
    val lokasi: String,
    val jenisAbsen: String,
    val tanggalAbsen: String,
    val shiftKerja: String,
    val foto: Bitmap
)
