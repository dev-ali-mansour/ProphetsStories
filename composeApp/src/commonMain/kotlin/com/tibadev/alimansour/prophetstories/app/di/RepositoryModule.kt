package com.tibadev.alimansour.prophetstories.app.di

import com.tibadev.alimansour.prophetstories.story.data.repository.DefaultStoriesRepository
import com.tibadev.alimansour.prophetstories.story.domain.repository.StoriesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<StoriesRepository> { 
        DefaultStoriesRepository()
    }
}
