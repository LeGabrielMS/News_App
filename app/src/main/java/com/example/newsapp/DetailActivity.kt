package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DetailActivity : AppCompatActivity() {
    private lateinit var newsTitle: TextView
    private lateinit var newsSubtitle: TextView
    private lateinit var newsImage: ImageView

    private lateinit var edit: Button
    private lateinit var hapus: Button
    private lateinit var db: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //inisialisasi UI Components
        newsTitle = findViewById(R.id.newsTitle)
        newsSubtitle = findViewById(R.id.newsSubtitle)
        newsImage = findViewById(R.id.newsImage)
        edit = findViewById(R.id.editButton)
        hapus = findViewById(R.id.deleteButton)

        //inisialisasi Firebase
        db = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()

        //get data from intent
        val intent = this.intent
        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val subtitle = intent.getStringExtra("desc")
        val imageUrl = intent.getStringExtra("imageUrl")

        //set data to UI Components
        newsTitle.text = title
        newsSubtitle.text = subtitle
        Glide.with(this).load(imageUrl).into(newsImage)

        // Menangani klik tombol kembali
        val back = findViewById<ImageView>(R.id.btnBack)
        back.setOnClickListener {
            val backIntent = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(backIntent)
            finish()
        }

        //edit news
        edit.setOnClickListener {
            val editIntent = Intent(this@DetailActivity, NewsAdd::class.java).apply {
                putExtra("id", id)
                putExtra("title", title)
                putExtra("desc", subtitle)
                putExtra("imageUrl", imageUrl)
            }
            startActivity(editIntent)
            finish()
        }

        //delete news
        hapus.setOnClickListener {
            id?.let { documentId ->
                db.collection("news")
                    .document(documentId)
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(
                            this@DetailActivity,
                            "News Deleted Succesfully",
                            Toast.LENGTH_LONG
                        ).show()

                        // Redirect to MainActivity
                        val mainIntent = Intent(this, MainActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                        startActivity(mainIntent)
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this@DetailActivity,
                            "Failed to delete news: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.w("News Detail, Error Deleting Document", e)
                    }
            }
        }
    }
}
