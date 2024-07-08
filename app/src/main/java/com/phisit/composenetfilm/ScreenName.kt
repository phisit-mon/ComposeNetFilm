package com.phisit.composenetfilm

import com.phisit.composenetfilm.domain.MovieModel
import com.phisit.composenetfilm.utils.serializableType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed class ScreenName

@Serializable
object HomeRouteScreen : ScreenName()

@Serializable
object SearchRouteScreen : ScreenName()

@Serializable
data class MovieDetailRouteScreen(
  val movieModel: MovieModel
) : ScreenName(){

  companion object{
    val type = mapOf(typeOf<MovieModel>() to serializableType<MovieModel>())
  }
}