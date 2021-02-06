package com.ranadata.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ranadata.data.database.entities.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDbDao
}
