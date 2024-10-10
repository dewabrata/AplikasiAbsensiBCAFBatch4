package com.juaracoding.aplikasiabsensi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.juaracoding.aplikasiabsensi.adapter.TutorialAdapter

class Tutorial : AppCompatActivity() {

    lateinit var  viewPager:  ViewPager2
    lateinit var   tAdapter: TutorialAdapter
    lateinit var  sharedPreferences: SharedPreferences
    val PREF_NAME = "Tutorial"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tutorial)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       var sharedPreferences = getSharedPreferences(PREF_NAME,0)

        val isFirstRun = sharedPreferences.getBoolean("isFirstRun",true)

        if(isFirstRun){
            sharedPreferences = getSharedPreferences(PREF_NAME,0)
            sharedPreferences.edit().putBoolean("isFirstRun",false).apply()

        }else{
            startActivity(Intent(this,Login::class.java))
            finish()
        }

        viewPager = findViewById(R.id.vPager)

         tAdapter = TutorialAdapter(this)
        viewPager.adapter =tAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    }
}