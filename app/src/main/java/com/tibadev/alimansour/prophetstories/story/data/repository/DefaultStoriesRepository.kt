package com.tibadev.alimansour.prophetstories.story.data.repository

import android.content.Context
import com.tibadev.alimansour.prophetstories.core.domain.DataError
import com.tibadev.alimansour.prophetstories.core.domain.Result
import com.tibadev.alimansour.prophetstories.story.data.dto.StoryDto
import com.tibadev.alimansour.prophetstories.story.data.mappers.toStory
import com.tibadev.alimansour.prophetstories.story.domain.model.Story
import com.tibadev.alimansour.prophetstories.story.domain.repository.StoriesRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class DefaultStoriesRepository(private val context: Context) : StoriesRepository {

    @OptIn(ExperimentalSerializationApi::class)
    override fun getStories(): Result<List<Story>, DataError.Local> = runCatching {
        val inputStream = context.assets.open("stories.json")
        val stories = Json.decodeFromStream<List<StoryDto>>(inputStream)
        Result.Success(stories.map { it.toStory() })
    }.getOrElse {
        Result.Error(DataError.Local.SERIALIZATION)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun getStoryDetails(prophet: String): Result<Story, DataError.Local> = runCatching {
        val inputStream = context.assets.open("stories.json")
        val stories = Json.decodeFromStream<List<StoryDto>>(inputStream)
        Result.Success(stories.first { it.prophet == prophet }.toStory())
    }.getOrElse {
        Result.Error(DataError.Local.SERIALIZATION)
    }
}