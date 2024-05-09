package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    // Deklarasi variabel binding dan auth
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menginisialisasi binding menggunakan ActivityRegisterBinding
        this.binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // Mengarahkan pengguna untuk pindah ke LoginActivity saat TextView "Sudah punya akun?" diklik
        this.binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Mendapatkan instance FirebaseAuth
        this.auth = FirebaseAuth.getInstance()

        // Saat Button "Register" diklik, Mendapatkan nilai email & password dari input teks dan mengonversinya menjadi string
        this.binding.buttonRegister.setOnClickListener {
            val email = binding.emailRegister.text.toString()
            val password = binding.passwordRegister.text.toString()

            // Memeriksa apakah email kosong
            if (email.isEmpty()) {
                binding.emailRegister.error = "Email Harus Di Isi!"
                binding.emailRegister.requestFocus()
                return@setOnClickListener
            }

            // Memeriksa apakah email memiliki format yang valid
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailRegister.error = "Email Tidak Valid!"
                binding.emailRegister.requestFocus()
                return@setOnClickListener
            }

            // Memeriksa apakah password kosong
            if (password.isEmpty()) {
                binding.passwordRegister.error = "Password Tidak Boleh Kosong!"
                binding.passwordRegister.requestFocus()
                return@setOnClickListener
            }

            // Memeriksa apakah password memiliki minimal 6 karakter
            if (password.length < 6) {
                binding.passwordRegister.error = "Password Minimal 6 Karakter!"
                binding.passwordRegister.requestFocus()
                return@setOnClickListener
            }

            // Memanggil fungsi registerFirebase untuk mendaftarkan pengguna baru
            registerFirebase(email, password)
        }
    }

    // Fungsi untuk mendaftarkan pengguna baru ke Firebase Authentication
    private fun registerFirebase(email: String, password: String) {
        this.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    // Jika pendaftaran berhasil, log pesan dan tampilkan toast
                    Log.d("RegisterActivity", "Email: $email baru saja mendaftar!")
                    Toast.makeText(this, "Register Berhasil!", Toast.LENGTH_LONG).show()

                    // Pindah ke LoginActivity setelah pendaftaran berhasil
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Jika pendaftaran gagal, tampilkan pesan error menggunakan exception.message
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}