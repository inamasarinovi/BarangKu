package com.example.barangku.repository

import com.example.barangku.data.Barang
import com.example.barangku.database.BarangDao
import kotlinx.coroutines.flow.Flow

class BarangRepository(
    private val barangDao: BarangDao
) {

    // Mengambil seluruh daftar barang
    fun getAllBarang(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    // Mengambil detail barang berdasarkan ID
    fun getBarangById(id: Int): Flow<Barang?> {
        return barangDao.getBarangById(id)
    }

    // Menambahkan barang
    suspend fun insertBarang(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    // Mengubah data barang
    suspend fun updateBarang(barang: Barang) {
        barangDao.updateBarang(barang)
    }

    // Menghapus barang
    suspend fun deleteBarang(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    // Mengubah status ketersediaan
    suspend fun updateStatus(barangId: Int, status: Boolean) {
        barangDao.updateStatus(barangId, status)
    }
}