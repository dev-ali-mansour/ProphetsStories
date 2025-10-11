package com.tibadev.alimansour.prophetstories.story.presentation.story_list

import com.tibadev.alimansour.prophetstories.core.domain.DataError
import com.tibadev.alimansour.prophetstories.story.domain.model.Story

data class StoryListState(
    val stories: List<Story> = emptyList(),
    val isLoading: Boolean = true,
    val error: DataError? = null
)