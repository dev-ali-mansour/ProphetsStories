package com.tibadev.alimansour.prophetstories.story.presentation

import androidx.lifecycle.ViewModel
import com.tibadev.alimansour.prophetstories.story.domain.model.Story
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedStoryViewModel : ViewModel() {
    private val _selectedStory = MutableStateFlow<Story?>(null)
    val selectedStory = _selectedStory.asStateFlow()


    fun onSelectStory(story: Story?) {
        _selectedStory.value = story
    }
}