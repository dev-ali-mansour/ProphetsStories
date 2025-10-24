package com.tibadev.alimansour.prophetstories.story.data.repository

import com.tibadev.alimansour.prophetstories.core.domain.DataError
import com.tibadev.alimansour.prophetstories.core.domain.Result
import com.tibadev.alimansour.prophetstories.core.util.readJsonResource
import com.tibadev.alimansour.prophetstories.story.data.dto.StoryDto
import com.tibadev.alimansour.prophetstories.story.data.mappers.toStory
import com.tibadev.alimansour.prophetstories.story.domain.model.Story
import com.tibadev.alimansour.prophetstories.story.domain.repository.StoriesRepository
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class DefaultStoriesRepository : StoriesRepository {

    private val json = Json { 
        ignoreUnknownKeys = true
        isLenient = true
    }
    
    private val cachedStories: List<StoryDto> by lazy {
        runBlocking {
            try {
                println("🔍 Loading stories.json...")
                val jsonString = readJsonResource("stories.json")
                println("✅ JSON loaded, length: ${jsonString.length}")
                val stories = json.decodeFromString<List<StoryDto>>(jsonString)
                println("✅ Parsed ${stories.size} stories")
                stories
            } catch (e: Exception) {
                println("❌ Error loading stories: ${e.message}")
                e.printStackTrace()
                emptyList()
            }
        }
    }

    override fun getStories(): Result<List<Story>, DataError.Local> = runCatching {
        println("📖 getStories called, cached stories count: ${cachedStories.size}")
        Result.Success(cachedStories.map { it.toStory() })
    }.getOrElse { e ->
        println("❌ Error in getStories: ${e.message}")
        e.printStackTrace()
        Result.Error(DataError.Local.SERIALIZATION)
    }

    override fun getStoryDetails(prophet: String): Result<Story, DataError.Local> = runCatching {
        Result.Success(cachedStories.first { it.prophet == prophet }.toStory())
    }.getOrElse { e ->
        e.printStackTrace()
        Result.Error(DataError.Local.SERIALIZATION)
    }
}