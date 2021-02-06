package com.ranadata.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ranadata.data.database.entities.WeatherEntity

@Dao
interface WeatherDbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: WeatherEntity)
}
