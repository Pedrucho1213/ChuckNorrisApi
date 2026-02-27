package com.example.chucknorrisapi.feature.jokes.domain.repository

import com.example.chucknorrisapi.feature.jokes.domain.model.Joke

interface JokesRepository {
    suspend fun getRandomJoke(category: String? = null): Result<Joke>
    suspend fun getCategories(): Result<List<String>>
    suspend fun searchJokes(query: String): Result<List<Joke>>
    suspend fun getJokeById(id: String): Result<Joke>
}