package com.tibadev.alimansour.prophetstories.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import prophetsstories.composeapp.generated.resources.Res
import prophetsstories.composeapp.generated.resources.helvetica_neuelt_w20_45_light
import prophetsstories.composeapp.generated.resources.helveticaneuelt_w20_55_roman
import prophetsstories.composeapp.generated.resources.helveticaneuelt_w20_75_bold

@Composable
fun getHelveticaFontFamily() = FontFamily(
    Font(
        resource = Res.font.helvetica_neuelt_w20_45_light,
        weight = FontWeight.Light
    ),
    Font(
        resource = Res.font.helveticaneuelt_w20_55_roman,
        weight = FontWeight.Normal
    ),
    Font(
        resource = Res.font.helveticaneuelt_w20_75_bold,
        weight = FontWeight.Bold
    )
)

@Composable
fun helveticaFamily() = getHelveticaFontFamily()

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
