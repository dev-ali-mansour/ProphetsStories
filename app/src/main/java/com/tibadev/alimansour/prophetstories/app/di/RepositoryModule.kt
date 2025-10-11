package com.tibadev.alimansour.prophetstories.app.di

import android.content.Context
import com.tibadev.alimansour.prophetstories.story.data.repository.DefaultStoriesRepository
import com.tibadev.alimansour.prophetstories.story.domain.repository.StoriesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideMessagesRepository(get()) }
}

fun provideMessagesRepository(context: Context): StoriesRepository =
    DefaultStoriesRepository(context)
