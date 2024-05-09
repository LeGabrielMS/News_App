package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    // Mendeklarasikan variabel binding dan auth
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menginisialisasi binding menggunakan ActivityLoginBinding
        this.binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // Mengarahkan pengguna untuk pindah ke RegisterActivity saat TextView "Belum punya akun?" diklik
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Mendapatkan instance FirebaseAuth
        this.auth = FirebaseAuth.getInstance()

        // Saat Button "Login" diklik, Mendapatkan nilai email & password dari input teks dan mengonversinya menjadi string
        this.binding.buttonLogin.setOnClickListener {
            val email = binding.emailLogin.text.toString()
            val password = binding.passwordLogin.text.toString()

            // Memeriksa apakah email kosong
            if (email.isEmpty()) {
                binding.emailLogin.error = "Email Harus Di Isi!"
                binding.emailLogin.requestFocus()
                return@setOnClickListener
            }

            // Memeriksa apakah email memiliki format yang valid
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailLogin.error = "Email Tidak Valid!"
                binding.emailLogin.requestFocus()
                return@setOnClickListener
            }

            // Memeriksa apakah password kosong
            if (password.isEmpty()) {
                binding.passwordLogin.error = "Password Tidak Boleh Kosong!"
                binding.passwordLogin.requestFocus()
                return@setOnClickListener
            }

            // Memeriksa apakah password memiliki minimal 6 karakter
            if (password.length < 6) {
                binding.passwordLogin.error = "Password Minimal 6 Karakter!"
                binding.passwordLogin.requestFocus()
                return@setOnClickListener
            }

            // Memanggil fungsi loginFirebase untuk melakukan autentikasi Firebase
            loginFirebase(email, password)
        }
    }

    // Fungsi untuk melakukan autentikasi Firebase menggunakan email dan password
    private fun loginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    // Jika autentikasi berhasil, arahkan pengguna ke MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Jika autentikasi gagal, tampilkan pesan error pada email dan fokuskan ke input email
                    binding.emailLogin.error = "Email atau Password Salah!"
                    binding.emailLogin.requestFocus()
                    return@addOnCompleteListener
                }
            }
    }
}