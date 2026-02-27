package com.example.chucknorrisapi.feature.jokes.domain.usecase

import com.example.chucknorrisapi.feature.jokes.domain.repository.JokesRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: JokesRepository
) {
    suspend operator fun invoke(): Result<List<String>> =
        repository.getCategories()
}