package com.mkhairulramadhan.favorite.di

import com.mkhairulramadhan.favorite.viewModel.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel {
        FavoriteViewModel(get())
    }
}