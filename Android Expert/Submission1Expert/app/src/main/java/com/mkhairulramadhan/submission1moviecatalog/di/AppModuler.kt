package com.mkhairulramadhan.submission1moviecatalog.di

import com.mkhairulramadhan.submission1moviecatalog.core.domain.usecase.GopoxMovieInteractor
import com.mkhairulramadhan.submission1moviecatalog.core.domain.usecase.GopoxMovieUseCase
import com.mkhairulramadhan.submission1moviecatalog.viewModel.DetailViewModel
import com.mkhairulramadhan.submission1moviecatalog.viewModel.FavoriteViewModel
import com.mkhairulramadhan.submission1moviecatalog.viewModel.MovieTvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GopoxMovieUseCase> { GopoxMovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieTvViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}