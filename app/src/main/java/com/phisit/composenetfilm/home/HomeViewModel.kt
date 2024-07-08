package com.phisit.composenetfilm.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phisit.composenetfilm.data.MovieRepository
import com.phisit.composenetfilm.domain.MovieModel
import kotlinx.coroutines.launch

class HomeViewModel(
   private val movieRepository: MovieRepository
) : ViewModel() {

    var _movieList = mutableStateListOf<MovieModel>()
    var _movieReverseList = mutableStateListOf<MovieModel>()

    val movieList: SnapshotStateList<MovieModel> = _movieList
    val movieReverseList: SnapshotStateList<MovieModel> = _movieReverseList

    init {
        getMovieList()
        getMovieReverseList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _movieList = _movieList.apply {
                clear()
                addAll(movieRepository.getMovies())
            }
        }
    }

    private fun getMovieReverseList() {
        viewModelScope.launch {
            _movieReverseList = movieReverseList.apply {
                clear()
                addAll(movieRepository.getMovies().reversed())
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

    fun updateReverseFavoriteMovie(movie: MovieModel, favorite: Boolean) {
        _movieReverseList.find {
            it.id == movie.id
        }?.let {
            it.isFavorite = favorite
        }
    }
}