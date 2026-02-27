package com.example.chucknorrisapi.feature.jokes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisapi.R
import com.example.chucknorrisapi.feature.jokes.domain.usecase.GetCategoriesUseCase
import com.example.chucknorrisapi.feature.jokes.domain.usecase.GetRandomJokesUseCase
import com.example.chucknorrisapi.feature.jokes.domain.usecase.SearchJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(
    private val getRandomJoke: GetRandomJokesUseCase,
    private val getCategories: GetCategoriesUseCase,
    private val searchJokes: SearchJokesUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(JokesUiState())
    val uiState: StateFlow<JokesUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
        loadRandomJoke()
    }

    fun onCategorySelected(category: String?){
        _uiState.update { it.copy(selectedCategory = category) }
        loadRandomJoke()
    }

    fun onQueryChanged(value: String){
        _uiState.update { it.copy(query = value, errorRes = null) }
    }


    fun onSearchClick(){
        val query = _uiState.value.query.trim()
        if (query.length < 3){
            _uiState.update { it.copy(errorRes = R.string.error_min_query) }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorRes = null) }
            searchJokes(query)
                .onSuccess { list -> _uiState.update { it.copy(isLoading = false, searchResults = list) }}
                .onFailure { e ->  _uiState.update { it.copy(isLoading = false, errorRes = e.toUiErrorRes()) } }
        }
    }

    fun loadRandomJoke(){
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorRes = null) }
            getRandomJoke(_uiState.value.selectedCategory)
                .onSuccess { joke -> _uiState.update { it.copy(isLoading = false, joke = joke) } }
                .onFailure { e -> _uiState.update { it.copy(isLoading = false, errorRes = e.toUiErrorRes()) } }
        }
    }


    private fun loadCategories() {
        viewModelScope.launch {
            getCategories()
                .onSuccess { list -> _uiState.update { it.copy(categories = list) } }
                .onFailure { e -> _uiState.update { it.copy(errorRes = e.toUiErrorRes()) } }
        }
    }

    private fun Throwable.toUiErrorRes(): Int = when (this){
        is IOException -> R.string.error_network
        is HttpException -> R.string.error_server
        else -> {
            Log.e("error", message, this)
            R.string.error_unknown
        }
    }

}