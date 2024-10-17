package com.juaracoding.aplikasiabsensi

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import java.util.jar.Manifest

class Login : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var imageLogo: ImageView
    private lateinit var txtGPS : TextView


    private val PREF_NAME = "LOGIN"
    private val USER_IS_LOGIN = "username"
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var auth : FirebaseAuth

    //tambahkan permission
    private val REQUEST_LOCATION_PERMISSION = 1
    private val REQUEST_COARSE_PERMISSION = 2

    private lateinit  var locationManager : LocationManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        checkPermissionLocation()

        val txtUsername = findViewById<EditText>(R.id.txtUsername)
        val txtPassword = findViewById<EditText>(R.id.editTextTextPassword)
        txtGPS = findViewById(R.id.txtGPS)

        txtUsername.setText( "")
        txtPassword.setText("")

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
            if (txtUsername.text.toString() != "" && txtPassword.text.toString() != "") {
               try {
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

            }catch (exception:Exception){
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
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


    fun checkPermissionLocation(){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_LOCATION_PERMISSION)


        }else{

            getLocation()
        }

        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),REQUEST_COARSE_PERMISSION)


        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_LOCATION_PERMISSION){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                getLocation()
            }
        }

        if(requestCode == REQUEST_COARSE_PERMISSION){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }

            }
    }


    fun getLocation(){

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5f,object:
                LocationListener {
                override fun onLocationChanged(location: Location) {
                    txtGPS.text = "Latitude "+location.latitude +" | Longitude " + location.longitude
                }

                override fun onProviderDisabled(provider: String) {
                    super.onProviderDisabled(provider)
                    Toast.makeText(this@Login,"GPS Disabled",Toast.LENGTH_LONG).show()
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    super.onStatusChanged(provider, status, extras)
                    Toast.makeText(this@Login,"Status Changed" + provider,Toast.LENGTH_LONG).show()
                }

            })
            return
        }



    }
}