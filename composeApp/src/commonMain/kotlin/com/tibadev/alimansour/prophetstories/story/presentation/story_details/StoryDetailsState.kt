package com.tibadev.alimansour.prophetstories.story.presentation.story_details

import com.tibadev.alimansour.prophetstories.core.domain.DataError
import com.tibadev.alimansour.prophetstories.story.domain.model.Story

data class StoryDetailsState(
    val isLoading: Boolean = false,
    val story: Story? = null,
    val error: DataError? = null
)