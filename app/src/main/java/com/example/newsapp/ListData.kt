package com.example.newsapp

// Kelas untuk merepresentasikan data berita
class ListData (
    var judul: String,  // Judul berita
    var tanggal: String, // Tanggal terbit berita
    var orang: Int, // Identitas orang yang mempublikasikan berita (dalam bentuk resource string)
    var konten: Int, // Isi konten berita (dalam bentuk resource string)
    var gambar: Int // Gambar yang terkait dengan berita (dalam bentuk resource drawable)
)
