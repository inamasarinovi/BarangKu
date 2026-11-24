package com.example.barangku.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.barangku.viewmodel.BarangViewModel

@Composable
fun DetailBarangScreen(
    barangId: Int,
    viewModel: BarangViewModel,
    onEdit: (Int) -> Unit,
    onKembali: () -> Unit,
    onHapusSelesai: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val barang = uiState.daftarBarang.find {
        it.id == barangId
    }

    var tampilkanDialogHapus by remember {
        mutableStateOf(false)
    }

    if (barang == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Text("Barang tidak ditemukan.")

            Button(onClick = onKembali) {
                Text("Kembali")
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Detail Barang",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Nama Barang: ${barang.namaBarang}"
        )

        Text(
            text = "Lokasi Penyimpanan: ${barang.lokasiSimpan}"
        )

        Text(
            text = "Keterangan: ${barang.keterangan}"
        )

        Text(
            text = if (barang.tersedia) {
                "Status: Tersedia"
            } else {
                "Status: Tidak tersedia"
            }
        )

        Button(
            onClick = {
                viewModel.ubahStatus(
                    barang.id,
                    !barang.tersedia
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (barang.tersedia) {
                    "Tandai Tidak Tersedia"
                } else {
                    "Tandai Tersedia"
                }
            )
        }

        OutlinedButton(
            onClick = {
                onEdit(barang.id)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Barang")
        }

        OutlinedButton(
            onClick = {
                tampilkanDialogHapus = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hapus Barang")
        }

        TextButton(onClick = onKembali) {
            Text("Kembali")
        }
    }

    if (tampilkanDialogHapus) {
        AlertDialog(
            onDismissRequest = {
                tampilkanDialogHapus = false
            },
            title = {
                Text("Hapus Barang?")
            },
            text = {
                Text("Apakah kamu yakin ingin menghapus barang ini?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.hapusBarang(barang)
                        tampilkanDialogHapus = false
                        onHapusSelesai()
                    }
                ) {
                    Text("Hapus")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        tampilkanDialogHapus = false
                    }
                ) {
                    Text("Batal")
                }
            }
        )
    }
}