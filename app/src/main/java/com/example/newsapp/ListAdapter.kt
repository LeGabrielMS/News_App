package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

// Kelas adapter untuk menampilkan daftar berita dalam ListView
class ListAdapter(context: Context, dataArrayList: ArrayList<ListData?>?) :
    ArrayAdapter<ListData?>(context, R.layout.lay_berita, dataArrayList!!) {

    // Override method getView untuk menampilkan item di posisi tertentu dalam ListView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView // Mendapatkan tampilan yang digunakan (item ListView) untuk posisi saat ini
        val listData = getItem(position) // Mendapatkan data berita dari posisi saat ini dalam daftar

        if (view == null) { // Jika tampilan belum ada (belum diinflate)
            // Inflate layout lay_berita menggunakan LayoutInflater
            view = LayoutInflater.from(context).inflate(R.layout.lay_berita, parent, false)
        }

        // Mendapatkan referensi ke ImageView dan TextView dalam layout lay_berita
        val listGambar = view!!.findViewById<ImageView>(R.id.img_news)
        val listJudul = view.findViewById<TextView>(R.id.tvw_title)
        val listTanggal = view.findViewById<TextView>(R.id.tvw_Tanggal)

        // Menetapkan data berita ke ImageView dan TextView dalam layout lay_berita
        listGambar.setImageResource(listData!!.gambar) // Menetapkan gambar berita
        listJudul.text = listData.judul // Menetapkan judul berita
        listTanggal.text = listData.tanggal // Menetapkan tanggal berita

        return view // Mengembalikan tampilan item berita yang telah diperbarui
    }
}
