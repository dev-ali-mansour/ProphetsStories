package com.tibadev.alimansour.prophetstories.story.domain.model

data class Story(
    var prophet: String = "",
    var content: String = ""
) {
    override fun toString(): String {
        return prophet
    }
}