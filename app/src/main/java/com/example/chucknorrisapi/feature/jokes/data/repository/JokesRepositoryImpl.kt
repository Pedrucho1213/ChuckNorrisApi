package com.example.chucknorrisapi.feature.jokes.data.repository

import com.example.chucknorrisapi.core.di.IoDispatcher
import com.example.chucknorrisapi.feature.jokes.data.mapper.toDomain
import com.example.chucknorrisapi.feature.jokes.data.remote.JokeRemoteDataSource
import com.example.chucknorrisapi.feature.jokes.domain.model.Joke
import com.example.chucknorrisapi.feature.jokes.domain.repository.JokesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val remote: JokeRemoteDataSource,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
): JokesRepository {

    override suspend fun getRandomJoke(category: String?): Result<Joke> =
        withContext(ioDispatcher) {
            runCatching {
                val dto = if (category.isNullOrBlank()) remote.getRandomJoke()
                else remote.getRandomJokeByCategory(category)
                dto.toDomain()
            }
        }

    override suspend fun getCategories(): Result<List<String>>  = withContext(ioDispatcher) {
        runCatching { remote.getCategories() }
    }

    override suspend fun searchJokes(query: String): Result<List<Joke>> =
        withContext(ioDispatcher) {
            runCatching { remote.searchJokes(query).result.map { it.toDomain() } }
        }

    override suspend fun getJokeById(id: String): Result<Joke> = withContext(ioDispatcher) {
        runCatching { remote.getJokeById(id).toDomain() }
    }
}