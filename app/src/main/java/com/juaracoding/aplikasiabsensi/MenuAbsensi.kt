package com.juaracoding.aplikasiabsensi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.juaracoding.aplikasiabsensi.model.Absensi

class MenuAbsensi : AppCompatActivity() {

    lateinit var fbAdd: FloatingActionButton
    private val absensiList = mutableListOf<Absensi>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_absensi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rbWFO)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fbAdd = findViewById(R.id.fabAbsen)

        fbAdd.setOnClickListener{

            val intent = Intent(this, AbsensiAdd::class.java)
            startActivityForResult(intent,100)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==100 && resultCode== 101){

            val absensi = data?.getParcelableExtra<Absensi>("absensi")

            if(absensi != null){
                absensiList.add(absensi)
            }


        }

    }
}