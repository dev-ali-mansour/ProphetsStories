package com.tibadev.alimansour.prophetstories.app.di

import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val appModule = module {
    single { Dispatchers.IO }
    single { AppUpdateManagerFactory.create(get()) }
}
