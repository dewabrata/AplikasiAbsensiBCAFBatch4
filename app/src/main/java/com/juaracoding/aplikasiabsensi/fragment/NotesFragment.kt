package com.juaracoding.aplikasiabsensi.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.juaracoding.aplikasiabsensi.R
import com.juaracoding.aplikasiabsensi.model.Notes

class NotesFragment : Fragment() {

    lateinit var sharedPreferences: SharedPreferences
    val PREF_NAME = "Notes"
    lateinit var txtNotes: EditText
    lateinit var txtJudulNote :EditText
    lateinit var btnSaveNotes:Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragement_notes, container, false)

        txtNotes = view.findViewById(R.id.txtNotes)
        txtJudulNote = view.findViewById(R.id.txtJudulNote)
        btnSaveNotes = view.findViewById(R.id.btnSaveNotes)

        btnSaveNotes.setOnClickListener{
            saveNotes(txtJudulNote.text.toString(),txtNotes.text.toString())


           val listFragment = parentFragmentManager.findFragmentById(R.id.frameListNotes) as ListFragment

            if(listFragment!=null){
                listFragment.updateDataSharedPreference(Notes(txtJudulNote.text.toString(),txtNotes.text.toString()))

                txtNotes.setText("")
                txtJudulNote.setText("")
            }


        }


        return view
    }

    fun saveNotes( judul:String,  isi:String){

        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME,0)
        val editor = sharedPreferences.edit()
        editor.putString(judul,isi)
        editor.apply()


    }

    fun loadNotes(judul:String):String{
        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME,0)
        return sharedPreferences.getString(judul,"").toString()

    }

}