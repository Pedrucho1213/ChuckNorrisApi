package com.example.chucknorrisapi.feature.jokes.data.mapper

import com.example.chucknorrisapi.feature.jokes.data.remote.api.dto.JokeDto
import com.example.chucknorrisapi.feature.jokes.domain.model.Joke

fun JokeDto.toDomain(): Joke = Joke(
    id = id,
    text = value,
    url = url,
    iconUrl = iconUrl,
    categories = categories
)