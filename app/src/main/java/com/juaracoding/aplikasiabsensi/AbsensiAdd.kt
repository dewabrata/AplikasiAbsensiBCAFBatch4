package com.juaracoding.aplikasiabsensi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AbsensiAdd : AppCompatActivity() {


    lateinit var txtNamaAbsen : EditText
    lateinit var txtLokasi : EditText
    lateinit var txtJenisAbsen : RadioGroup
    lateinit var txtTanggalAbsen :EditText
    lateinit var lstListKerja : Spinner
    lateinit var imgSelfie : ImageView
    lateinit var btnCapture : Button
    lateinit var btnSubmit  : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_absensi_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rbWFO)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()
    }


    fun initComponent(){
         txtNamaAbsen = findViewById<EditText>(R.id.txtNamaAbsen)
         txtLokasi = findViewById<EditText>(R.id.txtLokasi)
         txtJenisAbsen = findViewById<RadioGroup>(R.id.rgJenisAbsen)
         txtTanggalAbsen = findViewById<EditText>(R.id.txtTglAbsen)
         lstListKerja = findViewById<Spinner>(R.id.lstShiftKerja)
         imgSelfie = findViewById<ImageView>(R.id.imgSelfie)
         btnCapture = findViewById<Button>(R.id.btnCamera)
         btnSubmit = findViewById<Button>(R.id.btnSubmit)


        btnSubmit.setOnClickListener{
            var nama = txtNamaAbsen.text
            var lokasi = txtLokasi.text
            var selectedJenisAbsen  = txtJenisAbsen.checkedRadioButtonId

            val jenisAbsen = if (selectedJenisAbsen != -1 ) findViewById<RadioButton>(selectedJenisAbsen).text else ""

            val result = "Nama ${nama}} lokasi ${lokasi} jenis absen ${jenisAbsen}"

            Toast.makeText(this, result, Toast.LENGTH_LONG).show()


        }

    }
}