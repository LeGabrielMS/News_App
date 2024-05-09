package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Deklarasi variabel binding untuk mengakses tata letak XML
    private lateinit var binding: ActivityMainBinding

    // Deklarasi adapter dan dataArrayList untuk daftar berita
    private lateinit var newsAdapter: ListAdapter
    private lateinit var listData: ListData
    private var dataArrayList = ArrayList<ListData?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate layout menggunakan binding
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // Daftar gambar, orang, konten, judul, dan tanggal untuk setiap berita
        val gambarList = intArrayOf(
            R.drawable.img_news1,
            R.drawable.img_news2,
            R.drawable.img_news3,
            R.drawable.img_news4
        )

        val orangList = intArrayOf(
            R.string.timRedaksi1,
            R.string.timRedaksi2,
            R.string.timRedaksi3,
            R.string.timRedaksi4
        )

        val kontenList = intArrayOf(
            R.string.kontenBerita1,
            R.string.kontenBerita2,
            R.string.kontenBerita3,
            R.string.kontenBerita4
        )

        val judulList = arrayOf(
            "Polda Jatim Tangkap 3 Orang Pembuat Film Pendek 'Guru Tugas'",
            "Rumah Dua Lantai di Baubau Terbakar Hebat, Dua Penghuni Nyaris Terpanggang Api",
            "Satu Orang Tewas Saat Tertidur dalam Kebakaran Rumah di Tangsel",
            "Densus Dalami Keahlian DE Modifikasi Air Guns Menjadi Senjata Api"
        )

        val tanggalList = arrayOf(
            "Rabu, 08 Mei 2024 - 23:25 WIB",
            "Kamis, 29 Februari 2024 - 15:04 WIB",
            "Sabtu, 16 Maret 2024 - 18:45 WIB",
            "Selasa, 15 Agustus 2023 - 17:40 WIB"
        )

        // Memasukkan data berita ke dalam dataArrayList
        for (i in gambarList.indices) {
            this.listData = ListData(
                judulList[i],
                tanggalList[i],
                orangList[i],
                kontenList[i],
                gambarList[i]
            )
            this.dataArrayList.add(listData)
        }

        // Inisialisasi dan mengatur adapter untuk ListView
        this.newsAdapter = ListAdapter(this@MainActivity, this.dataArrayList)
        this.binding.lvDaftarBerita.adapter = this.newsAdapter
        this.binding.lvDaftarBerita.isClickable = true

        // Menangani klik item dalam ListView untuk membuka DetailActivity
        this.binding.lvDaftarBerita.onItemClickListener = OnItemClickListener { _, _, i, _ ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            // Menyampaikan data berita yang dipilih ke DetailActivity
            intent.putExtra("judul", judulList[i])
            intent.putExtra("tanggal", tanggalList[i])
            intent.putExtra("orang", orangList[i])
            intent.putExtra("konten", kontenList[i])
            intent.putExtra("gambar", gambarList[i])
            startActivity(intent)
        }

        // Menangani klik gambar profil untuk membuka AboutActivity
        this.binding.imgProfile.setOnClickListener {
            val intent = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}
