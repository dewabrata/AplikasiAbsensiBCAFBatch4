package com.juaracoding.aplikasiabsensi

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tutorial)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager = findViewById(R.id.vPager)

         tAdapter = TutorialAdapter(this)
        viewPager.adapter =tAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    }
}