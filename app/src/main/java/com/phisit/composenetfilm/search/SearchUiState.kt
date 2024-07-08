package com.phisit.composenetfilm.search

import com.phisit.composenetfilm.domain.MovieModel

sealed class SearchUiState {
    open val isLoading: Boolean = false
    open val isEmptyResult: Boolean = false
    open val movieList: List<MovieModel> = listOf()


    data class Loading(
        override val isLoading: Boolean = true
    ) : SearchUiState()

    data class EmptyResult(
        override val isEmptyResult: Boolean = true
    ) : SearchUiState()

    data class MovieList(
        override val movieList: List<MovieModel> = listOf()
    ) : SearchUiState()
}