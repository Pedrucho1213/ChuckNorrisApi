package com.example.chucknorrisapi.feature.jokes.data.remote.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchJokesResponseDto(
    @param:Json(name = "total") val total: Int,
    @param:Json(name = "result") val result: List<JokeDto>
)
