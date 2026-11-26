
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.barangku.viewmodel.SettingsViewModel

private val PinkBackground = Color(0xFFFFF7F8)
private val PinkPrimary = Color(0xFFD98296)
private val PinkSoft = Color(0xFFFCE8ED)
private val TextDark = Color(0xFF383238)
private val TextGray = Color(0xFF888087)

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onKembali: () -> Unit
) {
    val modeRingkas by viewModel.modeRingkas.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PinkBackground)
            .padding(20.dp)
    ) {
        Text(
            text = "Pengaturan",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Atur tampilan aplikasi BarangKu",
            fontSize = 14.sp,
            color = TextGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Mode Ringkas",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextDark
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Aktifkan tampilan daftar yang lebih ringkas",
                        fontSize = 13.sp,
                        color = TextGray
                    )
                }

                Switch(
                    checked = modeRingkas,
                    onCheckedChange = { aktif ->
                        viewModel.ubahModeRingkas(aktif)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = PinkPrimary,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = onKembali,
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
                text = "Kembali",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
