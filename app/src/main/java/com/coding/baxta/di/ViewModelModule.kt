package com.coding.baxta.di

import com.coding.baxta.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
}