package com.juaracoding.aplikasiabsensi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juaracoding.aplikasiabsensi.adapter.CreditAdapter
import com.juaracoding.aplikasiabsensi.viewmodel.PengajuanCreditViewModel

class PengajuanCreditList : AppCompatActivity() {

    lateinit var viewModel: PengajuanCreditViewModel


    lateinit var  lstPengajuanCredit: RecyclerView
    lateinit var btnTambahCredit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pengajuan_credit_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnTambahCredit = findViewById(R.id.btnTambahKredit)

        btnTambahCredit.setOnClickListener{
            val intent = Intent(this, UploadAllDocument::class.java)
            startActivity(intent)
        }

        lstPengajuanCredit = findViewById(R.id.lstPengajuanCredit)
        lstPengajuanCredit.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(PengajuanCreditViewModel::class.java)


        viewModel.getPengajuanCredit.observe(this)
        {


            var datax = it.data?.pengajuanCredit
           datax?.let { it1 ->

               lstPengajuanCredit.adapter = CreditAdapter(it1)

               lstPengajuanCredit.adapter?.notifyDataSetChanged()
           }


        }


    }

    override fun onResume() {
        super.onResume()
        viewModel.getPengajuanCredit()

    }
}