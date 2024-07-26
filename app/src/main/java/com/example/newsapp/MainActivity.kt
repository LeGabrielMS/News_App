package com.example.newsapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    // Deklarasi variabel-variabel yang diperlukan
    private lateinit var myAdapter: ListAdapter
    private lateinit var itemList: MutableList<ListData>
    private lateinit var db: FirebaseFirestore
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inisialisasi Firebase
        FirebaseApp.initializeApp(this)
        db = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()

        // Inisialisasi RecyclerView, floatingButton & ImageProfile
        val recyclerView = findViewById<RecyclerView>(R.id.lv_daftar_berita)
        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floatAddNews)
        val imgProfile = findViewById<ImageView>(R.id.img_profile)
        progressDialog = ProgressDialog(this@MainActivity).apply {
            setTitle("Loading...")
        }

        //Setup RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        itemList = ArrayList()
        myAdapter = ListAdapter(itemList)
        recyclerView.adapter = myAdapter

        // Menangani klik floatingButton untuk membuka NewsAdd
        floatingActionButton.setOnClickListener {
            val toAddPage = Intent(this@MainActivity, NewsAdd::class.java)
            startActivity(toAddPage)
        }

        // Menangani klik item dalam RecyclerView untuk membuka DetailActivity
        myAdapter.setOnItemClickListener(object : ListAdapter.OnItemClickListener {
            override fun onItemClick(item: ListData) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("id", item.id)
                    putExtra("title", item.judul)
                    putExtra("desc", item.deskripsi)
                    putExtra("imageUrl", item.imageUrl)
                }
                startActivity(intent)
            }
        })

        // Menangani klik gambar profil untuk membuka AboutActivity
        imgProfile.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        progressDialog.show()
        db.collection("news")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    itemList.clear()
                    for (document in task.result) {
                        val item = ListData(
                            document.id,
                            document.getString("title") ?: "",
                            document.getString("desc") ?: "",
                            document.getString("imageUrl") ?: ""
                        )
                        itemList.add(item)
                        Log.d("Data", "${document.id} => ${document.data}")
                    }
                    myAdapter.notifyDataSetChanged()
                } else {
                    Log.w("Data", "Error getting documents.", task.exception)
                }
                progressDialog.dismiss()
            }
    }

    override fun onStart() {
        super.onStart()
        //Fetch data from Firestore
        getData()
    }
}
