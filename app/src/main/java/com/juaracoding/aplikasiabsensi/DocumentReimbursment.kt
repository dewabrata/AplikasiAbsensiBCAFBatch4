package com.juaracoding.aplikasiabsensi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DocumentReimbursment : AppCompatActivity() {
    lateinit var btnUploadDoc: Button
    lateinit var txtUploadDoc :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_document_reimbursment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnUploadDoc = findViewById(R.id.btnUploadDocument)
        txtUploadDoc = findViewById(R.id.txtDocument)

        btnUploadDoc.setOnClickListener{

            openFile()
        }

    }

    fun openFile(){
        val intent = Intent().apply {
            type = "application/msword|application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            action =  Intent.ACTION_GET_CONTENT
        }
        val chooser = Intent.createChooser(intent,"Pilih Aplikasi untuk membuka Document",null)
        startActivityForResult(chooser,1)
    }
}