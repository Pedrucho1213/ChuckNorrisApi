package com.example.chucknorrisapi.feature.jokes.domain.model

data class Joke(
    val id: String,
    val text: String,
    val url: String,
    val iconUrl: String,
    val categories: List<String>
)
