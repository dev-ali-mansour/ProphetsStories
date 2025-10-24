package com.tibadev.alimansour.prophetstories

import android.app.Application
import com.tibadev.alimansour.prophetstories.app.di.appModule
import com.tibadev.alimansour.prophetstories.app.di.repositoryModule
import com.tibadev.alimansour.prophetstories.app.di.useCaseModule
import com.tibadev.alimansour.prophetstories.app.di.viewModelModule
import com.tibadev.alimansour.prophetstories.core.util.initializePlatformContext
import com.tibadev.alimansour.prophetstories.core.util.initializeVersionContext
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize platform actions context
        initializePlatformContext(this)
        
        // Initialize version context
        initializeVersionContext(this)

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                appModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}