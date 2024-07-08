package com.phisit.composenetfilm.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phisit.composenetfilm.data.MovieRepository
import com.phisit.composenetfilm.domain.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading())

    val uiState: StateFlow<SearchUiState> = _uiState
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            SearchUiState.Loading()
        )

    init {
        viewModelScope.launch {
            delay(1000)
            _uiState.value = SearchUiState.MovieList(movieRepository.getMovies())
        }
    }

    fun search(value: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val movies = movieRepository.searchMovie(value)
                if (movies.isEmpty()) {
                    _uiState.value = SearchUiState.EmptyResult()
                } else {
                    _uiState.value = SearchUiState.MovieList(movies)
                }
            }
        }
    }

    fun updateFavorite(movie: MovieModel, favorite: Boolean) {
        (_uiState.value as? SearchUiState.MovieList).let { movieListState ->
            movieListState?.movieList?.find {
                it.id == movie.id
            }?.let {
                it.isFavorite = favorite
            }
        }
    }
}