package com.example.montypays.settings.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.montypays.settings.data.Dao
import com.example.montypays.settings.data.ShareSettings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


//class Repository @Inject constructor(
//    private val dao: Dao
//) {
//    var shares :LiveData<List<ShareSettings>> = dao.getShares()
//
//    suspend fun save(shareSettings: ShareSettings){
//        dao.save(shareSettings)
//    }
//
//    suspend fun edit(shareSettings: ShareSettings){
//        dao.edit(shareSettings)
//    }
//
//    suspend fun delete(shareSettings: ShareSettings){
//        dao.delete(shareSettings)
//    }
//
//    fun searchDatabase(searchQuery: String): Flow<List<ShareSettings>> {
//        return dao.searchDatabase(searchQuery)
//    }
//
//    suspend fun getShareById(id: Int) : ShareSettings?
//
//}

interface Repository  {

    suspend fun save(shareSettings: ShareSettings)

    suspend fun edit(shareSettings: ShareSettings)

    suspend fun delete(shareSettings: ShareSettings)

    fun getShares(): LiveData<List<ShareSettings>>

    fun searchDatabase(searchQuery: String): Flow<List<ShareSettings>>

    suspend fun getShareById(id: Int): ShareSettings?
}