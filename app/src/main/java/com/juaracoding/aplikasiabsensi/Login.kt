package com.juaracoding.aplikasiabsensi

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var imageLogo: ImageView
    private val PREF_NAME = "LOGIN"
    private val USER_IS_LOGIN = "username"
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)


        val txtUsername = findViewById<EditText>(R.id.txtUsername)
        val txtPassword = findViewById<EditText>(R.id.editTextTextPassword)
        imageLogo = findViewById<ImageView>(R.id.logoImage)

        if(sharedPreferences.contains(USER_IS_LOGIN)){
            txtUsername.setText(sharedPreferences.getString(USER_IS_LOGIN,""))
        }

        val lblBcaFinance = findViewById<TextView>(R.id.lblBcaFinance)

        lblBcaFinance.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bcafinance.co.id"))
            startActivity(intent)
        }

        btnLogin = findViewById(R.id.btnLogin)

        btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener{
            val intent = Intent(this, Registrasi::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener {
            if (txtUsername.text.toString() != "") {
                auth.signInWithEmailAndPassword(txtUsername.text.toString(), txtPassword.text.toString())
                    .addOnCompleteListener(this){
                        if(it.isSuccessful){
                            rotateLogo(imageLogo)
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("username", txtUsername.text.toString())
                            startActivity(intent)
                        }
                    }
                    .addOnFailureListener(this){
                        Toast.makeText(this, "Login Gagal "+it.message, Toast.LENGTH_LONG).show()
                    }

//                val editor = sharedPreferences.edit()
//                editor.putString(USER_IS_LOGIN, txtUsername.text.toString())
//                editor.apply()
//
//
//                Toast.makeText(
//                    this, "Username : ${txtUsername.text} Password : " +
//                            "${txtPassword.text}", Toast.LENGTH_LONG
//                ).show()

            }else{
                Toast.makeText(this, "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }


        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rbWFO)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
      //  Toast.makeText(this, "Fungsi OnCreate Activate", Toast.LENGTH_LONG).show()


    }


    override fun onStart() {
        super.onStart()
     //   Toast.makeText(this, "Fungsi OnStart Activate", Toast.LENGTH_LONG).show()


    }

    override fun onRestart() {
        super.onRestart()
     //   Toast.makeText(this, "Fungsi OnRestart Activate", Toast.LENGTH_LONG).show()

    }

    override fun onResume() {
        super.onResume()
      //  Toast.makeText(this, "Fungsi OnResume Activate", Toast.LENGTH_LONG).show()
        rotateLogo(imageLogo)
    }

    override fun onStop() {
        super.onStop()
      //  Toast.makeText(this, "Fungsi OnStop Activate", Toast.LENGTH_LONG).show()

    }

    fun rotateLogo(logo: ImageView) {
        val animateLogo = AnimationUtils.loadAnimation(this, R.anim.logorotation)
        logo.startAnimation(animateLogo)
    }
}