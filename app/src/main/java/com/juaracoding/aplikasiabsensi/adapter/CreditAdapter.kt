package com.juaracoding.aplikasiabsensi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.juaracoding.aplikasiabsensi.R
import com.juaracoding.aplikasiabsensi.model.PengajuanCreditItem
import com.squareup.picasso.Picasso

class CreditAdapter(val creditList: List<PengajuanCreditItem?>) : RecyclerView.Adapter<CreditAdapter.CreditViewHolder>() {

    inner class CreditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtUsernameItemCredit = itemView.findViewById<TextView>(R.id.txtUsernameItemCredit)
        var txtTanggalItemCredit = itemView.findViewById<TextView>(R.id.txtTanggalItemCredit)
        var imgKtpItem = itemView.findViewById<ImageView>(R.id.imgKtpItem)
        var imgKKItem = itemView.findViewById<ImageView>(R.id.imgKKItem)
        var imgNPWPItem = itemView.findViewById<ImageView>(R.id.imgNPWPItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_credit, parent, false)

//        var llCredit= view.findViewById<LinearLayout>(R.id.linearLayoutCredit)
//        //get max height devices
//        val displayMetrics = view.context.resources.displayMetrics
//        val maxHeight = displayMetrics.heightPixels
//
//        llCredit.layoutParams.height = maxHeight / 4

        return CreditViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  creditList.size
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        val credit = creditList[position]

        holder.txtUsernameItemCredit.setText(credit?.username)
        holder.txtTanggalItemCredit.setText(credit?.tanggal)
        Picasso.get().load(credit?.fotoKtp).into(holder.imgKtpItem)
        Picasso.get().load(credit?.fotoKk).into(holder.imgKKItem)
        Picasso.get().load(credit?.fotoNpwp).into(holder.imgNPWPItem)

    }
}