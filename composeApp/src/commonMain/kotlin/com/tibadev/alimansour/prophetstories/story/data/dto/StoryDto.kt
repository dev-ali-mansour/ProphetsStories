package com.tibadev.alimansour.prophetstories.story.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoryDto(
    @SerialName("prophet") var prophet: String,
    @SerialName("story") var content: String
)
