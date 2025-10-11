package com.tibadev.alimansour.prophetstories.app

import kotlinx.serialization.Serializable


sealed class Route() {

    @Serializable
    data object StoryGraph : Route()
    @Serializable
    data object Splash : Route()

    @Serializable
    data object StoryList : Route()

    @Serializable
    data class StoryDetails(val prophet:String) : Route()

    @Serializable
    data object Settings : Route()

    @Serializable
    data object About : Route()
}