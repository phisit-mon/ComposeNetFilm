package com.phisit.composenetfilm.di

import com.phisit.composenetfilm.home.HomeViewModel
import com.phisit.composenetfilm.moviedetail.MovieDetailViewModel
import com.phisit.composenetfilm.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}