package com.juaracoding.aplikasiabsensi

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val username = intent.getStringExtra("username")
        val textView = findViewById<TextView>(R.id.textView3)
        textView.text = "Selamat Datang  \n, $username"

        val imgProfile = findViewById<ImageView>(R.id.imgProfile)
        imgProfile.setOnClickListener{
            val implicitIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //startActivity(implicitIntent)
            startActivityForResult(implicitIntent,99)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==99){
            val imgProfile = findViewById<ImageView>(R.id.imgProfile)
            imgProfile.setImageBitmap(data?.extras?.get("data") as Bitmap)
        }

    }


}