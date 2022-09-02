package com.example.montypays.authentication.domain

import com.example.montypays.authentication.data.AuthRepository
import com.example.montypays.authentication.data.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract  class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ) : AuthRepository
}