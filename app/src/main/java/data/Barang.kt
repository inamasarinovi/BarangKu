package com.example.barangku.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val namaBarang: String,
    val lokasiSimpan: String,
    val keterangan: String,
    val tersedia: Boolean = true
)