package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        val intent = this.intent
        if (intent != null) {
            val judul = intent.getStringExtra("judul")
            val tanggal = intent.getStringExtra("tanggal")
            val orang = intent.getIntExtra("orang", R.string.timRedaksi1)
            val konten = intent.getIntExtra("konten", R.string.kontenBerita1)
            val gambar = intent.getIntExtra("gambar", R.drawable.img_news1)

            this.binding.txtTitleNews.text = judul
            this.binding.txtPostTime.text = tanggal
            this.binding.txtReporter.setText(orang)
            this.binding.txtKontenNews.setText(konten)
            this.binding.imgToolbar.setImageResource(gambar)
        }

        this.binding.btnBack.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)
            finish()
        }
    }
}