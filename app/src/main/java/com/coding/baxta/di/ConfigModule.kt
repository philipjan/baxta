package com.coding.baxta.di

import com.coding.baxta.local.UserDb
import com.coding.baxta.network.RestClient
import org.koin.dsl.module


val networkModule = module {
    single {
        RestClient.provideHttpClient()
    }

    single {
        UserDb.create(get())
    }
}
