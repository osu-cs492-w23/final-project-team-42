package com.example.team42fitness.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.team42fitness.data.locationLookback.LocationDao
import com.example.team42fitness.data.locationLookback.LocationData

@Database(entities = [LocationData::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    // Daos
    abstract fun locationDao(): LocationDao


    // singleton instantiation
    companion object {
        @Volatile
        private var instance: AppDatabase? = null


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "42Fitness.db"
            ).build()

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

    }
}