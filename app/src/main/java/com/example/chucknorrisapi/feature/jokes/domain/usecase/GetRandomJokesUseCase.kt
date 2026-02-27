package com.example.chucknorrisapi.feature.jokes.domain.usecase

import com.example.chucknorrisapi.feature.jokes.domain.model.Joke
import com.example.chucknorrisapi.feature.jokes.domain.repository.JokesRepository
import javax.inject.Inject

class GetRandomJokesUseCase @Inject constructor(
    private val repository: JokesRepository
){
    suspend operator fun invoke(category: String? = null): Result<Joke> =
    repository.getRandomJoke(category)
}