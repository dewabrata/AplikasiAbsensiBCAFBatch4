package com.juaracoding.aplikasiabsensi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.juaracoding.aplikasiabsensi.R
import com.juaracoding.aplikasiabsensi.model.PengajuanCreditItem

class CreditAdapter(val creditList: List<PengajuanCreditItem>) : RecyclerView.Adapter<CreditAdapter.CreditViewHolder>() {

    inner class CreditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_credit, parent, false)

        var llCredit= view.findViewById<LinearLayout>(R.id.linearLayoutCredit)
        //get max height devices
        val displayMetrics = view.context.resources.displayMetrics
        val maxHeight = displayMetrics.heightPixels

        llCredit.layoutParams.height = maxHeight / 4



        return CreditViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        val credit = creditList[position]
    }
}