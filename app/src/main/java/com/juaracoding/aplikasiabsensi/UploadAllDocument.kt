package com.juaracoding.aplikasiabsensi

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UploadAllDocument : AppCompatActivity() {

    lateinit var txtUsername : EditText
    lateinit var txtTanggal : EditText
    lateinit var btnKTP : Button
    lateinit var btnKK : Button
    lateinit var btnNPWP : Button
    lateinit var btnSendData : Button
    lateinit var imgKtp : ImageView
    lateinit var imgKK : ImageView
    lateinit var imgNPWP : ImageView
    lateinit var imageUriKK :Bitmap
    lateinit var imageUriKTP :Bitmap
    lateinit var imageUriNPWP :Bitmap


    fun initComponent(){
        txtUsername = findViewById(R.id.txtUsernameCredit)
        txtTanggal = findViewById(R.id.txtTglCredit)
        btnKTP = findViewById(R.id.btnKTP)
        btnKK = findViewById(R.id.btnKK)
        btnNPWP = findViewById(R.id.btnNPWP)
        btnSendData = findViewById(R.id.btnSendData)
        imgKtp = findViewById(R.id.imgKTP)
        imgKK = findViewById(R.id.imgKK)
        imgNPWP = findViewById(R.id.imgNPWP)

        btnKTP.setOnClickListener{
            selectImageKTP()
        }

        btnKK.setOnClickListener{
            selectImageKK()
        }
        btnNPWP.setOnClickListener{
            selectImageNPWP()
        }

        btnSendData.setOnClickListener{
            sendData()
        }

    }

    fun selectImageKTP(){
        val implicitIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncherKtp.launch(implicitIntent)

    }

    private val resultLauncherKtp = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUriKTP = result.data?.extras?.get("data") as Bitmap
            imgKtp.setImageBitmap(imageUriKTP)
        }

    }

    fun selectImageKK(){
        val implicitIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncherKK.launch(implicitIntent)
    }

    private val resultLauncherKK = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUriKK = result.data?.extras?.get("data") as Bitmap
            imgKK.setImageBitmap(imageUriKK)
        }

    }

    fun selectImageNPWP(){
        val implicitIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncherNPWP.launch(implicitIntent)
    }

    private val resultLauncherNPWP = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUriNPWP = result.data?.extras?.get("data") as Bitmap
            imgNPWP.setImageBitmap(imageUriNPWP)
        }

    }

    fun sendData(){

        val username = txtUsername.text.toString()
        val tanggal = txtTanggal.text.toString()

        if (username.isNotEmpty() && tanggal.isNotEmpty() && imageUriKK != null && imageUriKTP != null && imageUriNPWP != null){




        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_all_document)
        initComponent()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}