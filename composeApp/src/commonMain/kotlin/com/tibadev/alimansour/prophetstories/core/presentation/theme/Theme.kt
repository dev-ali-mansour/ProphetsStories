package com.tibadev.alimansour.prophetstories.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = primaryDarkColor,
    onPrimary = primaryVariantColor,
    primaryContainer = secondaryColor,
)
private val DarkColorScheme = darkColorScheme(
    primary = primaryLightColor,
    onPrimary = primaryVariantColor,
    primaryContainer = secondaryColor,
)

@Composable
fun ProphetStoriesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}