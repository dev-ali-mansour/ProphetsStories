package com.tibadev.alimansour.prophetstories.core.util

import prophetsstories.composeapp.generated.resources.Res

suspend fun readJsonResource(fileName: String): String {
    val bytes = Res.readBytes("files/$fileName")
    return bytes.decodeToString()
}
