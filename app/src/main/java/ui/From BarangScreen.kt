
package com.example.barangku.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.barangku.data.Barang
import com.example.barangku.viewmodel.BarangViewModel

private val PinkBackground = Color(0xFFFFF7F8)
private val PinkPrimary = Color(0xFFD98296)
private val PinkSoft = Color(0xFFFCE8ED)
private val TextDark = Color(0xFF383238)
private val TextGray = Color(0xFF888087)

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
            .background(PinkBackground)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = if (modeEdit) "Edit Barang" else "Tambah Barang",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )

        Text(
            text = if (modeEdit) {
                "Perbarui informasi barangmu"
            } else {
                "Masukkan informasi barang baru"
            },
            fontSize = 14.sp,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = namaBarang,
            onValueChange = {
                namaBarang = it
                pesanError = ""
            },
            label = { Text("Nama Barang") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PinkPrimary,
                focusedLabelColor = PinkPrimary,
                cursorColor = PinkPrimary,
                unfocusedBorderColor = Color(0xFFE5DADD),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        OutlinedTextField(
            value = lokasiSimpan,
            onValueChange = {
                lokasiSimpan = it
            },
            label = { Text("Lokasi Penyimpanan") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PinkPrimary,
                focusedLabelColor = PinkPrimary,
                cursorColor = PinkPrimary,
                unfocusedBorderColor = Color(0xFFE5DADD),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        OutlinedTextField(
            value = keterangan,
            onValueChange = {
                keterangan = it
            },
            label = { Text("Keterangan") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PinkPrimary,
                focusedLabelColor = PinkPrimary,
                cursorColor = PinkPrimary,
                unfocusedBorderColor = Color(0xFFE5DADD),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = PinkSoft,
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = tersedia,
                onCheckedChange = {
                    tersedia = it
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = PinkPrimary,
                    uncheckedColor = TextGray,
                    checkmarkColor = Color.White
                )
            )

            Text(
                text = "Barang tersedia",
                color = TextDark,
                fontSize = 14.sp
            )
        }

        if (pesanError.isNotEmpty()) {
            Text(
                text = pesanError,
                color = MaterialTheme.colorScheme.error,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

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
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PinkPrimary,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Simpan",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        OutlinedButton(
            onClick = onSelesai,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = PinkPrimary
            ),
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                PinkPrimary
            )
        ) {
            Text(
                text = "Batal",
                fontSize = 15.sp
            )
        }
    }
}
