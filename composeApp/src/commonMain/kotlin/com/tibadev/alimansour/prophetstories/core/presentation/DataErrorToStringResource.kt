package com.tibadev.alimansour.prophetstories.core.presentation

import com.tibadev.alimansour.prophetstories.core.domain.DataError


fun DataError.asString(): String {
    return when (this) {
        DataError.Local.DISK_FULL -> "Disk is full"
        DataError.Local.SERIALIZATION -> "Data serialization error"
        DataError.Local.UNKNOWN -> "Unknown local error"
        DataError.Remote.NO_INTERNET -> "No internet connection"
        DataError.Remote.REQUEST_TIMEOUT -> "Request timed out"
        DataError.Remote.TOO_MANY_REQUESTS -> "Too many requests"
        DataError.Remote.SERVER -> "Server error"
        DataError.Remote.SERIALIZATION -> "Data serialization error"
        DataError.Remote.UNKNOWN -> "Unknown error"
    }
}
