package com.tibadev.alimansour.prophetstories.app.di

import com.tibadev.alimansour.prophetstories.story.presentation.SelectedStoryViewModel
import com.tibadev.alimansour.prophetstories.story.presentation.settings.SettingsViewModel
import com.tibadev.alimansour.prophetstories.story.presentation.story_details.StoryDetailsViewModel
import com.tibadev.alimansour.prophetstories.story.presentation.story_list.StoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StoryListViewModel(get()) }
    viewModel { StoryDetailsViewModel(get(), get()) }
    viewModel { SelectedStoryViewModel() }
    viewModel { SettingsViewModel() }
}