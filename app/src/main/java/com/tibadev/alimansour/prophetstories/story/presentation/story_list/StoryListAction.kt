package com.tibadev.alimansour.prophetstories.story.presentation.story_list

import com.tibadev.alimansour.prophetstories.story.domain.model.Story

sealed interface StoryListAction {
    data class OnStoryClick(val story: Story) : StoryListAction
}