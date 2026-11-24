package com.example.barangku.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.barangku.data.Barang
import com.example.barangku.viewmodel.BarangViewModel

@Composable
fun FormBarangScreen(
    barangId: Int?,
    viewModel: BarangViewModel,
    onSelesai: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val barang = uiState.daftarBarang.find {
        it.id == barangId
    }

    var namaBarang by remember(barangId) {
        mutableStateOf(barang?.namaBarang ?: "")
    }

    var lokasiSimpan by remember(barangId) {
        mutableStateOf(barang?.lokasiSimpan ?: "")
    }

    var keterangan by remember(barangId) {
        mutableStateOf(barang?.keterangan ?: "")
    }

    var tersedia by remember(barangId) {
        mutableStateOf(barang?.tersedia ?: true)
    }

    var pesanError by remember {
        mutableStateOf("")
    }

    val modeEdit = barangId != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = if (modeEdit) "Edit Barang" else "Tambah Barang",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = namaBarang,
            onValueChange = {
                namaBarang = it
                pesanError = ""
            },
            label = { Text("Nama Barang") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = lokasiSimpan,
            onValueChange = { lokasiSimpan = it },
            label = { Text("Lokasi Penyimpanan") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = keterangan,
            onValueChange = { keterangan = it },
            label = { Text("Keterangan") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Checkbox(
                checked = tersedia,
                onCheckedChange = { tersedia = it }
            )

            Text("Barang tersedia")
        }

        if (pesanError.isNotEmpty()) {
            Text(
                text = pesanError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                if (namaBarang.isBlank()) {
                    pesanError = "Nama barang tidak boleh kosong."
                } else {
                    val dataBarang = Barang(
                        id = barang?.id ?: 0,
                        namaBarang = namaBarang.trim(),
                        lokasiSimpan = lokasiSimpan.trim(),
                        keterangan = keterangan.trim(),
                        tersedia = tersedia
                    )

                    if (modeEdit) {
                        viewModel.ubahBarang(dataBarang)
                    } else {
                        viewModel.tambahBarang(dataBarang)
                    }

                    onSelesai()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }

        OutlinedButton(
            onClick = onSelesai,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Batal")
        }
    }
}