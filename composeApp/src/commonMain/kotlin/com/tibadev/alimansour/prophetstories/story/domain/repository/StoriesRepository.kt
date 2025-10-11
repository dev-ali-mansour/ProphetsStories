package com.tibadev.alimansour.prophetstories.story.domain.repository

import com.tibadev.alimansour.prophetstories.core.domain.DataError
import com.tibadev.alimansour.prophetstories.core.domain.Result
import com.tibadev.alimansour.prophetstories.story.domain.model.Story

interface StoriesRepository {
    fun getStories(): Result<List<Story>, DataError.Local>
    fun getStoryDetails(prophet: String): Result<Story, DataError.Local>
}