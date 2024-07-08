package com.phisit.composenetfilm.moviedetail

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phisit.composenetfilm.data.MovieRepository
import com.phisit.composenetfilm.domain.MovieModel
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private var _movieList = mutableStateListOf<MovieModel>()

    val movieList: SnapshotStateList<MovieModel> = _movieList

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _movieList = _movieList.apply {
                clear()
                addAll(movieRepository.getMovies())
            }
        }
    }

    fun updateFavoriteMovie(movie: MovieModel, favorite: Boolean) {
        _movieList.find {
            it.id == movie.id
        }?.let {
            it.isFavorite = favorite
        }
    }
}