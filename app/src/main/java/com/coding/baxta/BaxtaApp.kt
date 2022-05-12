package com.coding.baxta

import android.app.Application
import com.coding.baxta.di.UserModule
import com.coding.baxta.di.networkModule
import com.coding.baxta.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaxtaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaxtaApp)
            modules(
                listOf(
                    networkModule,
                    viewModelsModule,
                    UserModule.getUserUseCaseModule,
                    UserModule.localUserSourceModule,
                    UserModule.networkUserSourceModule,
                    UserModule.userRepositoryModule
                )
            )
        }
    }
}