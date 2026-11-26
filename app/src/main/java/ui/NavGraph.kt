
package com.example.barangku.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.barangku.viewmodel.BarangViewModel
import com.example.barangku.viewmodel.SettingsViewModel

@Composable
fun BarangKuNavGraph(
    navController: NavHostController,
    barangViewModel: BarangViewModel,
    settingsViewModel: SettingsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.DaftarBarang.route
    ) {

        // HALAMAN DAFTAR BARANG
        composable(
            route = Screen.DaftarBarang.route
        ) {
            DaftarBarangScreen(
                viewModel = barangViewModel,
                settingsViewModel = settingsViewModel,
                onTambahBarang = {
                    navController.navigate(
                        Screen.FormBarang.createRoute()
                    )
                },
                onDetailBarang = { barangId ->
                    navController.navigate(
                        Screen.DetailBarang.createRoute(barangId)
                    )
                },
                onPengaturan = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        // HALAMAN DETAIL BARANG
        composable(
            route = Screen.DetailBarang.route,
            arguments = listOf(
                navArgument("barangId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val barangId =
                backStackEntry.arguments?.getInt("barangId") ?: 0

            DetailBarangScreen(
                barangId = barangId,
                viewModel = barangViewModel,
                onEdit = { id ->
                    navController.navigate(
                        Screen.FormBarang.createRoute(id)
                    )
                },
                onKembali = {
                    navController.popBackStack()
                },
                onHapusSelesai = {
                    navController.popBackStack()
                }
            )
        }

        // HALAMAN TAMBAH DAN EDIT BARANG
        composable(
            route = Screen.FormBarang.route,
            arguments = listOf(
                navArgument("barangId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->

            val barangId = backStackEntry.arguments
                ?.getString("barangId")
                ?.toIntOrNull()

            FormBarangScreen(
                barangId = barangId,
                viewModel = barangViewModel,
                onSelesai = {
                    navController.popBackStack()
                }
            )
        }

        // HALAMAN SETTINGS
        composable(
            route = Screen.Settings.route
        ) {
            SettingsScreen(
                viewModel = settingsViewModel,
                onKembali = {
                    navController.popBackStack()
                }
            )
        }
    }
}
