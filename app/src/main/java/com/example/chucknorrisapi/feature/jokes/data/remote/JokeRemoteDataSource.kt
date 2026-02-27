package com.example.chucknorrisapi.feature.jokes.data.remote

import com.example.chucknorrisapi.feature.jokes.data.remote.api.ChuckNorrisApiService
import com.example.chucknorrisapi.feature.jokes.data.remote.api.dto.JokeDto
import com.example.chucknorrisapi.feature.jokes.data.remote.api.dto.SearchJokesResponseDto
import com.example.chucknorrisapi.feature.jokes.domain.model.Joke
import javax.inject.Inject

class JokeRemoteDataSource @Inject constructor(
    private val api: ChuckNorrisApiService
) {
    suspend fun getRandomJoke(): JokeDto = api.getRandomJoke()
    suspend fun getRandomJokeByCategory(category: String): JokeDto = api.getRandomJokeByCategory(category)
    suspend fun getCategories(): List<String> = api.getCategories()
    suspend fun searchJokes(query: String): SearchJokesResponseDto = api.searchJokes(query)
    suspend fun getJokeById(id: String): JokeDto = api.getJokeById(id)
}