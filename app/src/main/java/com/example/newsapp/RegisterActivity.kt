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
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        this.auth = FirebaseAuth.getInstance()

        this.binding.buttonRegister.setOnClickListener {
            val email = binding.emailRegister.text.toString()
            val password = binding.passwordRegister.text.toString()

            if (email.isEmpty()) {
                binding.emailRegister.error = "Email Harus Di Isi!"
                binding.emailRegister.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailRegister.error = "Email Tidak Valid!"
                binding.emailRegister.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.passwordRegister.error = "Password Tidak Boleh Kosong!"
                binding.passwordRegister.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.passwordRegister.error = "Password Minimal 6 Karakter!"
                binding.passwordRegister.requestFocus()
                return@setOnClickListener
            }

            registerFirebase(email, password)
        }
    }

    private fun registerFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Log.d("RegisterActivity", "Email: $email baru saja mendaftar!")
                    Toast.makeText(this, "Register Berhasil!", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}