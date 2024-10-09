package com.juaracoding.aplikasiabsensi

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import com.juaracoding.aplikasiabsensi.fragment.ListFragment
import com.juaracoding.aplikasiabsensi.fragment.NotesFragment

class NotesApp : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notes_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(resources.configuration.orientation==Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction().replace(R.id.frameListNotes, ListFragment())
                .commit()
        }else{
            supportFragmentManager.beginTransaction().replace(R.id.frameListNotes, NotesFragment())
                .commit()

        }

    }
}