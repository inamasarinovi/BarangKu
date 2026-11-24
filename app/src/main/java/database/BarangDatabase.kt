package com.example.barangku.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.barangku.data.Barang

@Database(
    entities = [Barang::class],
    version = 1,
    exportSchema = false
)
abstract class BarangDatabase : RoomDatabase() {

    abstract fun barangDao(): BarangDao

    companion object {
        @Volatile
        private var INSTANCE: BarangDatabase? = null

        fun getDatabase(context: Context): BarangDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BarangDatabase::class.java,
                    "barang_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}