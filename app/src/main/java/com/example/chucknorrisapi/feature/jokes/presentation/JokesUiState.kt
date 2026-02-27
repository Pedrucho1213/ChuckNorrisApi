package com.example.chucknorrisapi.feature.jokes.presentation

import androidx.annotation.StringRes
import com.example.chucknorrisapi.feature.jokes.domain.model.Joke

data class JokesUiState (
    val isLoading: Boolean = false,
    val joke: Joke? = null,
    val categories: List<String> = emptyList(),
    val selectedCategory: String? = null,
    val query: String = "",
    val searchResults: List<Joke> = emptyList(),
    @param:StringRes val errorRes: Int? = null
)