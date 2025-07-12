package com.tibadev.alimansour.prophetstories

import androidx.multidex.MultiDexApplication
import com.tibadev.alimansour.prophetstories.app.di.appModule
import com.tibadev.alimansour.prophetstories.app.di.repositoryModule
import com.tibadev.alimansour.prophetstories.app.di.useCaseModule
import com.tibadev.alimansour.prophetstories.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber


class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

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
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}