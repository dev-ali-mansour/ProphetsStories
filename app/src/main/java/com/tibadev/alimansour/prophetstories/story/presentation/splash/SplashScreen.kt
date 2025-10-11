package com.tibadev.alimansour.prophetstories.story.presentation.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.app.Route
import com.tibadev.alimansour.prophetstories.core.presentation.theme.SPLASH_LOGO_SIZE
import com.tibadev.alimansour.prophetstories.core.presentation.theme.primaryLightColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.primaryVariantColor


@Composable
fun SplashScreen(navController: NavHostController) {
    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )

        navController.popBackStack()
        navController.navigate(Route.StoryList)

    }

    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees: Float) {
    val modifier = if (isSystemInDarkTheme()) Modifier.background(Color.Black)
    else Modifier.background(Brush.verticalGradient(listOf(primaryVariantColor, primaryLightColor)))
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(SPLASH_LOGO_SIZE)
                .rotate(degrees = degrees),
            painter = painterResource(id = R.drawable.ic_launcher),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenPreview() {
    Splash(degrees = 0f)
}