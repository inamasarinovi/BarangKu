package com.example.barangku.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.barangku.viewmodel.BarangViewModel
import com.example.barangku.viewmodel.SettingsViewModel

private val PinkBackground = Color(0xFFFFF7F8)
private val PinkPrimary = Color(0xFFD98296)
private val PinkSoft = Color(0xFFFCE8ED)
private val TextDark = Color(0xFF383238)
private val TextGray = Color(0xFF888087)
private val GreenSoft = Color(0xFFE5F3EA)
private val GreenText = Color(0xFF398052)
private val RedSoft = Color(0xFFFCE8E8)
private val RedText = Color(0xFFC45D65)

@Composable
fun DaftarBarangScreen(
    viewModel: BarangViewModel,
    settingsViewModel: SettingsViewModel,
    onTambahBarang: () -> Unit,
    onDetailBarang: (Int) -> Unit,
    onPengaturan: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val modeRingkas by settingsViewModel.modeRingkas
        .collectAsStateWithLifecycle()

    Scaffold(
        containerColor = PinkBackground,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onTambahBarang,
                containerColor = PinkPrimary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "BarangKu",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )

                    Text(
                        text = "Kelola barang pribadimu",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextGray
                    )
                }

                TextButton(onClick = onPengaturan) {
                    Text(
                        text = "Pengaturan",
                        color = PinkPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Daftar Barang",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDark
                )

                Text(
                    text = "${uiState.daftarBarang.size} barang",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextGray
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            if (uiState.daftarBarang.isEmpty()) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Belum ada barang.\nTambahkan barang pertamamu!",
                        color = TextGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 90.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        if (modeRingkas) 8.dp else 12.dp
                    )
                ) {
                    items(
                        items = uiState.daftarBarang,
                        key = { it.id }
                    ) { barang ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onDetailBarang(barang.id)
                                },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 1.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(
                                    if (modeRingkas) 12.dp else 16.dp
                                )
                            ) {

                                Text(
                                    text = barang.namaBarang,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextDark
                                )

                                if (!modeRingkas) {
                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = "Lokasi: ${barang.lokasiSimpan}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = TextGray
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier
                                        .background(
                                            color = if (barang.tersedia)
                                                GreenSoft else RedSoft,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(
                                            horizontal = 10.dp,
                                            vertical = 5.dp
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = if (barang.tersedia)
                                            "Tersedia" else "Tidak tersedia",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Medium,
                                        color = if (barang.tersedia)
                                            GreenText else RedText
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
