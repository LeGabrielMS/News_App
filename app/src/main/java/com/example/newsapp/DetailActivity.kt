package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    // Variabel binding untuk mengakses tata letak XML
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate layout menggunakan binding
        this.binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // Mendapatkan intent dari Activity sebelumnya
        val intent = this.intent
        if (intent != null) {
            // Mendapatkan data berita dari intent
            val judul = intent.getStringExtra("judul")
            val tanggal = intent.getStringExtra("tanggal")
            val orang = intent.getIntExtra("orang", R.string.timRedaksi1)
            val konten = intent.getIntExtra("konten", R.string.kontenBerita1)
            val gambar = intent.getIntExtra("gambar", R.drawable.img_news1)

            // Menampilkan data berita ke UI
            this.binding.txtTitleNews.text = judul
            this.binding.txtPostTime.text = tanggal
            this.binding.txtReporter.setText(orang)
            this.binding.txtKontenNews.setText(konten)
            this.binding.imgToolbar.setImageResource(gambar)
        }

        // Menangani klik tombol kembali
        this.binding.btnBack.setOnClickListener {
            // Membuat intent untuk kembali ke MainActivity
            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent) // Memulai Activity MainActivity
            finish() // Menutup Activity saat ini (DetailActivity)
        }
    }
}
