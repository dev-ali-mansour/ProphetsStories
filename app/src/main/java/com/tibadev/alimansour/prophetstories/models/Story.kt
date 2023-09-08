package com.tibadev.alimansour.prophetstories.models

data class Story(
    var prophet: String = "",
    var content: String = ""
) {
    override fun toString(): String {
        return prophet
    }
}