package com.tibadev.alimansour.prophetstories

import com.tibadev.alimansour.prophetstories.app.di.appModule
import com.tibadev.alimansour.prophetstories.app.di.repositoryModule
import com.tibadev.alimansour.prophetstories.app.di.useCaseModule
import com.tibadev.alimansour.prophetstories.app.di.viewModelModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            appModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    }
}

