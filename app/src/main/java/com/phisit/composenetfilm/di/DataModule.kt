package com.phisit.composenetfilm.di

import com.phisit.composenetfilm.data.MovieRepository
import com.phisit.composenetfilm.data.MovieRepositoryImp
import org.koin.dsl.module

val dataModule = module {

    factory <MovieRepository>{ MovieRepositoryImp() }
}