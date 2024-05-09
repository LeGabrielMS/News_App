package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: ListAdapter
    private lateinit var listData: ListData
    private var dataArrayList = ArrayList<ListData?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

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

        this.newsAdapter = ListAdapter(this@MainActivity, this.dataArrayList)
        this.binding.rcvDaftarBerita.adapter = this.newsAdapter
        this.binding.rcvDaftarBerita.isClickable = true

        this.binding.rcvDaftarBerita.onItemClickListener = OnItemClickListener { _, _, i, _ ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("judul", judulList[i])
            intent.putExtra("tanggal", tanggalList[i])
            intent.putExtra("orang", orangList[i])
            intent.putExtra("konten", kontenList[i])
            intent.putExtra("gambar", gambarList[i])
            startActivity(intent)
        }
    }
}