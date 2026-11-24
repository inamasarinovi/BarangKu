package com.example.barangku.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "pengaturan_barang"
)

class UserPreferencesRepository(
    private val context: Context
) {

    private val modeRingkasKey =
        booleanPreferencesKey("mode_ringkas")

    val modeRingkas: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[modeRingkasKey] ?: false
        }

    suspend fun simpanModeRingkas(aktif: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[modeRingkasKey] = aktif
        }
    }
}