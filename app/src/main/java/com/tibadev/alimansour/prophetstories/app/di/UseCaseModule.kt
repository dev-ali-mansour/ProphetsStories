package com.tibadev.alimansour.prophetstories.app.di

import com.tibadev.alimansour.prophetstories.story.domain.usecase.GetStories
import com.tibadev.alimansour.prophetstories.story.domain.usecase.GetStoryDetails
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val useCaseModule = module {

    singleOf(::GetStories)
    singleOf(::GetStoryDetails)
}