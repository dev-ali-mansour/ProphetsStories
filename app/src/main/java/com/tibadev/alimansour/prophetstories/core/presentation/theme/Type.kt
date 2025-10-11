package com.tibadev.alimansour.prophetstories.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tibadev.alimansour.prophetstories.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
val helveticaFamily = FontFamily(
    Font(R.font.helvetica_neuelt_w20_45_light, FontWeight.Light),
    Font(R.font.helveticaneuelt_w20_55_roman, FontWeight.Normal),
    Font(R.font.helveticaneuelt_w20_75_bold, FontWeight.Bold)
)