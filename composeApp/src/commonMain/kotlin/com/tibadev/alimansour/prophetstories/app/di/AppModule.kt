package com.tibadev.alimansour.prophetstories.app.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val appModule = module {
    single { Dispatchers.Default }
}
