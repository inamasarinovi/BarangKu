
package com.example.barangku.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.barangku.viewmodel.BarangViewModel

@Composable
fun DaftarBarangScreen(
    viewModel: BarangViewModel,
    onTambahBarang: () -> Unit,
    onDetailBarang: (Int) -> Unit,
    onPengaturan: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onTambahBarang
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "BarangKu",
                    style = MaterialTheme.typography.headlineMedium
                )

                TextButton(
                    onClick = onPengaturan
                ) {
                    Text("Pengaturan")
                }
            }

            Text(
                text = "Daftar Barang Pribadi",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (uiState.daftarBarang.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Belum ada barang. Yuk, tambahkan barang!")
                }

            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.daftarBarang) { barang ->

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onDetailBarang(barang.id)
                            }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = barang.namaBarang,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = "Lokasi: ${barang.lokasiSimpan}"
                                )

                                Text(
                                    text = if (barang.tersedia) {
                                        "Status: Tersedia"
                                    } else {
                                        "Status: Tidak tersedia"
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
