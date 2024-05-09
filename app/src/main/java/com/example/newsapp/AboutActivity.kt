package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    // Deklarasi variabel binding untuk mengakses tata letak XML
    private lateinit var binding : ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout menggunakan binding
        this.binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // Menangani klik tombol kembali
        this.binding.btnBack.setOnClickListener {
            // Membuat intent untuk kembali ke MainActivity
            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent) // Memulai Activity MainActivity
            finish() // Menutup Activity saat ini (AboutActivity)
        }

    }
}
