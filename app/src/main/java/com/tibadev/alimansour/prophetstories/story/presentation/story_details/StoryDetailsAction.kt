package com.tibadev.alimansour.prophetstories.story.presentation.story_details

import com.tibadev.alimansour.prophetstories.story.domain.model.Story

sealed interface StoryDetailsAction {
    data object OnBackClick : StoryDetailsAction
    data object OnZoomInClick : StoryDetailsAction
    data object OnZoomOutClick : StoryDetailsAction
    data class OnSelectedStoryChange(val story: Story) : StoryDetailsAction
}