package com.example.montypays.settings.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: Dao
) : Repository {
    override suspend fun save(shareSettings: ShareSettings) {
        dao.save(shareSettings)
    }

    override suspend fun edit(shareSettings: ShareSettings) {
        dao.edit(shareSettings)
    }

    override suspend fun delete(shareSettings: ShareSettings) {
        dao.delete(shareSettings)
    }

    override fun getShares(): LiveData<List<ShareSettings>>  = dao.getShares()

    override fun searchDatabase(searchQuery: String): Flow<List<ShareSettings>> {
        return dao.searchDatabase(searchQuery)
    }

    override suspend fun getShareById(id: Int): ShareSettings? {
        return dao.getShareById(id)
    }
}