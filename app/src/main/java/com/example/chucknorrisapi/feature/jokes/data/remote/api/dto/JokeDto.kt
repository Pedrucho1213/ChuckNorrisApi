package com.example.chucknorrisapi.feature.jokes.data.remote.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JokeDto (
    @param:Json(name = "id") val id: String,
    @param:Json(name = "value") val value : String,
    @param:Json(name = "url") val url : String,
    @param:Json(name = "icon_url") val iconUrl: String,
    @param:Json(name = "categories") val categories: List<String>
)