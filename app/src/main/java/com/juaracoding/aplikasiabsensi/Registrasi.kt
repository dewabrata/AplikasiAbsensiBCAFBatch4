package com.juaracoding.aplikasiabsensi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Registrasi : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtConfirmPassword: EditText
    private lateinit var btnRegister: Button

    //firebase auth
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrasi)
        //inisasi firebase
        auth = FirebaseAuth.getInstance()

        initComponent()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rbWFO)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun initComponent(){
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        txtConfirmPassword = findViewById(R.id.txtPassword2)

        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener{
            if(txtEmail.text.toString() != "" && txtPassword.text.toString() != "" && txtConfirmPassword.text.toString() != ""){
                if(txtPassword.text.toString() == txtConfirmPassword.text.toString()){
                    auth.createUserWithEmailAndPassword(txtEmail.text.toString(), txtPassword.text.toString())
                        .addOnCompleteListener(this){
                            if(it.isSuccessful){
                                finish()
                            }
                        }
                        .addOnFailureListener(this){
                            Toast.makeText(this, "Registrasi Gagal "+it.message, Toast.LENGTH_LONG).show()
                        }
                }else{
                    Toast.makeText(this, "Password Tidak Sama", Toast.LENGTH_LONG).show()
                }
                }else{

            }
        }


    }
}