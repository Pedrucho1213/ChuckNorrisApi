package com.example.chucknorrisapi.core.di

import com.example.chucknorrisapi.feature.jokes.domain.repository.JokesRepository
import com.example.chucknorrisapi.feature.jokes.domain.repository.JokesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindJokesRepository(
        impl: JokesRepositoryImpl
    ): JokesRepository
}