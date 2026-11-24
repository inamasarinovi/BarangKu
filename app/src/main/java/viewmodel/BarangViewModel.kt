package com.example.barangku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.barangku.data.Barang
import com.example.barangku.repository.BarangRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class BarangUiState(
    val daftarBarang: List<Barang> = emptyList()
)

class BarangViewModel(
    private val repository: BarangRepository
) : ViewModel() {

    val uiState: StateFlow<BarangUiState> =
        repository.getAllBarang()
            .map { daftar ->
                BarangUiState(daftarBarang = daftar)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = BarangUiState()
            )

    fun tambahBarang(barang: Barang) {
        viewModelScope.launch {
            repository.insertBarang(barang)
        }
    }

    fun ubahBarang(barang: Barang) {
        viewModelScope.launch {
            repository.updateBarang(barang)
        }
    }

    fun hapusBarang(barang: Barang) {
        viewModelScope.launch {
            repository.deleteBarang(barang)
        }
    }

    fun ubahStatus(id: Int, status: Boolean) {
        viewModelScope.launch {
            repository.updateStatus(id, status)
        }
    }
}