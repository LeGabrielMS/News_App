package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        this.auth = FirebaseAuth.getInstance()

        this.binding.buttonLogin.setOnClickListener {
            val email = binding.emailLogin.text.toString()
            val password = binding.passwordLogin.text.toString()

            if (email.isEmpty()) {
                binding.emailLogin.error = "Email Harus Di Isi!"
                binding.emailLogin.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailLogin.error = "Email Tidak Valid!"
                binding.emailLogin.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.passwordLogin.error = "Password Tidak Boleh Kosong!"
                binding.passwordLogin.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.passwordLogin.error = "Password Minimal 6 Karakter!"
                binding.passwordLogin.requestFocus()
                return@setOnClickListener
            }

            loginFirebase(email, password)
        }
    }

    private fun loginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    binding.emailLogin.error = "Email atau Password Salah!"
                    binding.emailLogin.requestFocus()
                    return@addOnCompleteListener

                }
            }

    }
}