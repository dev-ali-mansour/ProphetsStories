package com.tibadev.alimansour.prophetstories.story.domain.usecase

import com.tibadev.alimansour.prophetstories.core.domain.DataError
import com.tibadev.alimansour.prophetstories.core.domain.Result
import com.tibadev.alimansour.prophetstories.story.domain.model.Story
import com.tibadev.alimansour.prophetstories.story.domain.repository.StoriesRepository


class GetStories(private val repository: StoriesRepository) {

    operator fun invoke(): Result<List<Story>, DataError.Local> = repository.getStories()
}