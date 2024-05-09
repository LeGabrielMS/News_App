package com.example.newsapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

// Anotasi untuk menandai penggunaan custom splash screen dan mencegah lint warning
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Menggunakan Handler untuk menunda perpindahan ke LoginActivity selama 3 detik (3000L)
        Handler(Looper.getMainLooper()).postDelayed({
            // Memanggil fungsi goToLoginActivity setelah penundaan selesai
            goToLoginActivity()
        }, 3000L)
    }

    // Fungsi untuk membuka LoginActivity
    private fun goToLoginActivity() {
        // Membuat Intent untuk membuka LoginActivity
        Intent(this, LoginActivity::class.java).also {
            // Memulai aktivitas baru dengan Intent yang telah dibuat
            startActivity(it)
            // Menutup SplashScreenActivity agar tidak kembali saat tombol "back" ditekan
            finish()
        }
    }
}