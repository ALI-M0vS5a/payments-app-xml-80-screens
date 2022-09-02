package com.example.montypays.settings.data


import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [ShareSettings::class],
    version = 2
)
abstract class Database : RoomDatabase() {
    abstract val dao: Dao
}
