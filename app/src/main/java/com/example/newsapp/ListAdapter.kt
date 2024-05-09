package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(context: Context, dataArrayList: ArrayList<ListData?>?) :
    ArrayAdapter<ListData?>(context, R.layout.lay_berita, dataArrayList!!) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val listData = getItem(position)

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.lay_berita, parent, false)
        }

        val listGambar = view!!.findViewById<ImageView>(R.id.img_news)
        val listJudul = view.findViewById<TextView>(R.id.tvw_title)
        val listTanggal = view.findViewById<TextView>(R.id.tvw_Tanggal)

        listGambar.setImageResource(listData!!.gambar)
        listJudul.text = listData.judul
        listTanggal.text = listData.tanggal

        return view
    }
}