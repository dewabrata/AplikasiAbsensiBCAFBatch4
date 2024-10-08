package com.juaracoding.aplikasiabsensi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.juaracoding.aplikasiabsensi.adapter.AbsensiAdapter
import com.juaracoding.aplikasiabsensi.model.Absensi

class MenuAbsensi : AppCompatActivity() {

    lateinit var fbAdd: FloatingActionButton
    private val absensiList = mutableListOf<Absensi>()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: AbsensiAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_absensi)
        val username = intent.getStringExtra("username")


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rbWFO)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.lstAbsensi)
        setupRecyclerView()

        fbAdd = findViewById(R.id.fabAbsen)

        fbAdd.setOnClickListener{

            val intent = Intent(this, AbsensiAdd::class.java)
            intent.putExtra("username", username)
            startActivityForResult(intent,100)

        }
    }

    fun setupRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AbsensiAdapter(absensiList)
        recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==100 && resultCode== 101){

            val absensi = data?.getParcelableExtra<Absensi>("absensi")

            if(absensi != null){
                absensiList.add(absensi)
                adapter.notifyItemInserted(absensiList.size-1)
            }


        }

    }
}