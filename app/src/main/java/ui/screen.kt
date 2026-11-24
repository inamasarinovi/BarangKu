package com.example.barangku.ui

sealed class Screen(val route: String) {

    data object DaftarBarang : Screen("daftar_barang")

    data object DetailBarang : Screen("detail_barang/{barangId}") {
        fun createRoute(barangId: Int): String {
            return "detail_barang/$barangId"
        }
    }

    data object FormBarang : Screen("form_barang?barangId={barangId}") {
        fun createRoute(barangId: Int? = null): String {
            return if (barangId == null) {
                "form_barang"
            } else {
                "form_barang?barangId=$barangId"
            }
        }
    }

    data object Settings : Screen("settings")
}