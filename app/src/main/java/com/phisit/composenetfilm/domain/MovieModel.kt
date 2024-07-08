package com.phisit.composenetfilm.domain

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class MovieModel(
    val id: String,
    val name: String,
    val description: String = "",
    val posterUrl: String? = null,
    val voting: Int = 0,
    val rating: String = "",
    val comment: String = "",
    private val initialFavorite: Boolean = false,
    val releaseDate: String? = null,
    val duration: Int = 0,
    val language: List<String>? = null,
    val actors: List<String>? = null
){

    var isFavorite by mutableStateOf(initialFavorite)
}