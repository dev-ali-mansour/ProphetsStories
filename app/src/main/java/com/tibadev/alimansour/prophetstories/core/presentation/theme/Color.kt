package com.tibadev.alimansour.prophetstories.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryDarkColor = Color(0xFF9C4F0C)
val primaryLightColor = Color(0xFFC38957)
val primaryVariantColor = Color(0xFF116888)
val secondaryColor = Color(0xFF73a1cf)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)

val statusBarColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else primaryVariantColor

val screenBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Color(0xFFb2daed)

val titleColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else DarkGray

val descriptionColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray.copy(alpha = 0.5f)
    else DarkGray.copy(alpha = 0.5f)

val activeIndicatorColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) primaryVariantColor else primaryLightColor

val inactiveIndicatorColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else LightGray


val contentBackgroundColor: Color
    @Composable
    get() = backgroundColor.copy(alpha = 0.6f)

val backgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else primaryLightColor

val buttonBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) primaryVariantColor else primaryLightColor

val contentColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else Color.White

val barBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else primaryLightColor

val tintColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.LightGray else Color.Black


val textColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Color.White