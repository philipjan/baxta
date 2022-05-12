package com.coding.baxta.di

import com.coding.baxta.local.UserDb
import com.coding.baxta.local.user.UserLocalSource
import com.coding.baxta.network.user.UserNetworkSource
import com.coding.baxta.repository.user.IUserRepository
import com.coding.baxta.repository.user.UserRepositoryImpl
import com.coding.baxta.usecase.GetUsersUseCase
import com.coding.baxta.usecase.WatchUserUseCase
import org.koin.dsl.module

object UserModule {

    val userRepositoryModule = module {
        factory<IUserRepository> { UserRepositoryImpl(get(), get(), get()) }
    }

    val getUserUseCaseModule = module {
        factory { GetUsersUseCase(get()) }
        factory { WatchUserUseCase(get()) }
    }

    val networkUserSourceModule = module {
        factory { UserNetworkSource(get()) }
    }

    val localUserSourceModule = module {
        factory { UserDb.create(get()).userDao() }
        factory { UserLocalSource(get()) }
    }

}