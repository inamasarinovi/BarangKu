package com.example.barangku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.barangku.repository.BarangRepository
import com.example.barangku.repository.UserPreferencesRepository

class BarangKuViewModelFactory(
    private val barangRepository: BarangRepository,
    private val preferencesRepository: UserPreferencesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        return when {
            modelClass.isAssignableFrom(BarangViewModel::class.java) -> {
                BarangViewModel(barangRepository) as T
            }

            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> {
                SettingsViewModel(preferencesRepository) as T
            }

            else -> throw IllegalArgumentException(
                "Unknown ViewModel class: ${modelClass.name}"
            )
        }
    }
}