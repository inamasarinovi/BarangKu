package com.example.barangku.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.example.barangku.data.Barang

@Dao
interface BarangDao {

    // Menampilkan semua barang
    @Query("SELECT * FROM barang ORDER BY id DESC")
    fun getAllBarang(): Flow<List<Barang>>

    // Mengambil detail barang berdasarkan ID
    @Query("SELECT * FROM barang WHERE id = :id")
    fun getBarangById(id: Int): Flow<Barang?>

    // Menambahkan barang
    @Insert
    suspend fun insertBarang(barang: Barang)

    // Mengubah data barang
    @Update
    suspend fun updateBarang(barang: Barang)

    // Menghapus barang
    @Delete
    suspend fun deleteBarang(barang: Barang)

    // Mengubah status ketersediaan barang
    @Query("UPDATE barang SET tersedia = :status WHERE id = :barangId")
    suspend fun updateStatus(barangId: Int, status: Boolean)
}