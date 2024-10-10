package com.juaracoding.aplikasiabsensi.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.juaracoding.aplikasiabsensi.fragment.TutorialDua
import com.juaracoding.aplikasiabsensi.fragment.TutorialSatu

class TutorialAdapter (activity:AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
           return when (position){
               0 -> TutorialSatu.newInstance("","")
               1 -> TutorialDua.newInstance("","")


               else -> throw IllegalArgumentException("position tidak ditemukan")
           }

    }


}