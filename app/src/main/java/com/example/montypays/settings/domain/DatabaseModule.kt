package com.example.montypays.settings.domain

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.montypays.settings.data.Database
import com.example.montypays.settings.data.Repository
import com.example.montypays.settings.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "share_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRepository(database: Database) : Repository {
        return RepositoryImpl(database.dao)
    }

}