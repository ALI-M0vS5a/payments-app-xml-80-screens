package com.example.montypays.settings.data


import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.montypays.settings.data.ShareSettings
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(shareSettings: ShareSettings)

    @Update
    suspend fun edit(shareSettings: ShareSettings)

    @Delete
    suspend fun delete(shareSettings: ShareSettings)

    @Query(" Select * From share " + " ORDER BY id DESC ")
    fun getShares(): LiveData<List<ShareSettings>>

    @Query(" SELECT * FROM share WHERE merchantName LIKE :searchQuery" + " ORDER BY id DESC")
    fun searchDatabase(searchQuery: String): Flow<List<ShareSettings>>


    @Query(" SELECT * FROM share WHERE id = :id")
    fun getShareById(id: Int): ShareSettings?
}