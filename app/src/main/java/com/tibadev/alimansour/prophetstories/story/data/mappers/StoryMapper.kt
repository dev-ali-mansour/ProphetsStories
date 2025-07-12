package com.tibadev.alimansour.prophetstories.story.data.mappers

import com.tibadev.alimansour.prophetstories.story.data.dto.StoryDto
import com.tibadev.alimansour.prophetstories.story.domain.model.Story

fun StoryDto.toStory() = Story(
    prophet = prophet,
    content = content
)

fun Story.toStoryDto() = StoryDto(
    prophet = prophet,
    content = content
)