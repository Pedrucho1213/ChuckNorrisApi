package com.example.chucknorrisapi.feature.jokes.data.remote.api

import com.example.chucknorrisapi.feature.jokes.data.remote.api.dto.JokeDto
import com.example.chucknorrisapi.feature.jokes.data.remote.api.dto.SearchJokesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChuckNorrisApiService {

    @GET("jokes/random")
    suspend fun getRandomJoke(): JokeDto

    @GET("jokes/random")
    suspend fun getRandomJokeByCategory(
        @Query("category") category: String
    ): JokeDto

    @GET("jokes/categories")
    suspend fun getCategories(): List<String>

    @GET("jokes/search")
    suspend fun searchJokes(
        @Query("query") query: String
    ): SearchJokesResponseDto

    @GET("jokes/{id}")
    suspend fun getJokeById(
        @Path("id") id: String
    ): JokeDto
}