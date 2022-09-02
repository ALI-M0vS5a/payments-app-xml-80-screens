package com.example.montypays.settings.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "share")
data class ShareSettings(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "merchantName")
    val merchantName: String,
    @ColumnInfo(name = "shareContent")
    val shareContent: String
)
