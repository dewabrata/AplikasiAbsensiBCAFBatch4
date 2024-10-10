package com.juaracoding.aplikasiabsensi.fragment

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juaracoding.aplikasiabsensi.R
import com.juaracoding.aplikasiabsensi.adapter.NotesAdapter
import com.juaracoding.aplikasiabsensi.model.Notes

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var sharedPreferences: SharedPreferences
    val PREF_NAME = "Notes"
    lateinit var  isiNotes : MutableList<Notes>
    lateinit var  listNotes:RecyclerView
    lateinit var  btnTambahNotes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        listNotes = view.findViewById<RecyclerView>(R.id.lstNotes)
        btnTambahNotes = view.findViewById(R.id.btnTambahNotes)

        sharedPreferences = requireActivity().getSharedPreferences(PREF_NAME,0)
        loadSharedPreferences()

//        val dataDummy = mutableListOf<Notes>()
//        dataDummy.add(Notes("Catatan Si Boy", "Ini catatannya"))
        listNotes.layoutManager = LinearLayoutManager(requireContext())

        listNotes.adapter = NotesAdapter(isiNotes){
            notes: Notes ->
            Toast.makeText(requireContext(), notes.isi, Toast.LENGTH_SHORT).show()

            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val fragmentNotes =
                    requireActivity().supportFragmentManager.findFragmentById(R.id.frameNotes)

                if (fragmentNotes != null) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.lstNotes, fragmentNotes).commit()
                } else {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frameListNotes, NotesFragment.newInstance(notes))
                        .addToBackStack("notes")
                        .commit()
                }
            }else{
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameNotes, NotesFragment.newInstance(notes))
                    .commit()
            }
        }


        btnTambahNotes.setOnClickListener{
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameListNotes, NotesFragment()).commit()
            }else{
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameNotes, NotesFragment()).commit()
            }

        }


        return view
    }

    fun loadSharedPreferences(){
        val allNotes = sharedPreferences.all
        isiNotes = mutableListOf<Notes>()


        for((key,value) in allNotes){
            val notes = Notes(key,value.toString())
            isiNotes.add(notes)


        }

    }

    fun updateDataSharedPreference (notes: Notes){

        loadSharedPreferences()
     var isSameContent = false
     for(i in isiNotes){
         if(i.judul == notes.judul){
             isSameContent = true
         }
     }
        if(!isSameContent) {
            isiNotes.add(notes)
            listNotes.adapter?.notifyItemInserted(isiNotes.size - 1)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}