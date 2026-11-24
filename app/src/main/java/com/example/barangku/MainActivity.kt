
package com.example.barangku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.barangku.database.BarangDatabase
import com.example.barangku.repository.BarangRepository
import com.example.barangku.repository.UserPreferencesRepository
import com.example.barangku.ui.BarangKuNavGraph
import com.example.barangku.ui.theme.BarangKuTheme
import com.example.barangku.viewmodel.BarangKuViewModelFactory
import com.example.barangku.viewmodel.BarangViewModel
import com.example.barangku.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Membuat database Room
        val database = BarangDatabase.getDatabase(applicationContext)

        // Membuat Repository
        val barangRepository = BarangRepository(
            database.barangDao()
        )

        val preferencesRepository = UserPreferencesRepository(
            applicationContext
        )

        // Membuat ViewModel Factory
        val factory = BarangKuViewModelFactory(
            barangRepository,
            preferencesRepository
        )

        // Membuat BarangViewModel
        val barangViewModel = ViewModelProvider(
            this,
            factory
        )[BarangViewModel::class.java]

        val settingsViewModel = ViewModelProvider(
            this,
            factory
        )[SettingsViewModel::class.java]

        setContent {
            BarangKuTheme {
                val navController = rememberNavController()

                BarangKuNavGraph(
                    navController = navController,
                    barangViewModel = barangViewModel,
                    settingsViewModel = settingsViewModel
                )
            }
        }
    }
}