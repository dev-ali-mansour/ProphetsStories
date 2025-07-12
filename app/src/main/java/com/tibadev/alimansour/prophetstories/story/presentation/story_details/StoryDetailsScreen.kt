package com.tibadev.alimansour.prophetstories.story.presentation.story_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.core.presentation.theme.primaryLightColor
import com.tibadev.alimansour.prophetstories.story.presentation.story_details.components.DetailsTopBar

@Composable
fun StoryDetailsScreen(
    viewModel: StoryDetailsViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var contentFontSize by remember { mutableStateOf(20.sp) }
    StoryDetailsContent(
        state = state,
        contentFontSize = contentFontSize,
        onAction = {
            when (it) {
                is StoryDetailsAction.OnBackClick -> onBackClick()
                is StoryDetailsAction.OnZoomInClick -> {
                    contentFontSize =
                        if (contentFontSize < 30.sp)
                            contentFontSize + 2.sp
                        else contentFontSize
                }

                is StoryDetailsAction.OnZoomOutClick -> {
                    contentFontSize = if (contentFontSize > 16.sp)
                        contentFontSize - 2.sp
                    else contentFontSize
                }

                else -> Unit
            }
            viewModel.onAction(it)
        }
    )

}

@Composable
fun StoryDetailsContent(
    state: StoryDetailsState,
    contentFontSize: TextUnit,
    onAction: (StoryDetailsAction) -> Unit,
) {
    Scaffold(
        topBar = {
            DetailsTopBar(
                onBackClick = { onAction(StoryDetailsAction.OnBackClick) },
                onZoomInClicked = { onAction(StoryDetailsAction.OnZoomInClick) },
                onZoomOutClicked = { onAction(StoryDetailsAction.OnZoomOutClick) },
            )
        },
        content = { paddingValues ->

            state.story?.let { story ->

                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.content_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = story.prophet,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(top = 10.dp),
                                color = primaryLightColor,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.headlineMedium
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(top = 10.dp, bottom = 20.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Text(
                                    text = story.content,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp),
                                    color = Color.Black,
                                    fontSize = contentFontSize,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyLarge,
                                    textAlign = TextAlign.Justify,
                                )
                            }
                        }
                    }
                }
            }
        }
    )

}

operator fun TextUnit.plus(other: TextUnit): TextUnit {
    if (this.type != other.type)
        throw IllegalArgumentException("Cannot add TextUnits of different types: ${this.type} and ${other.type}")
    return TextUnit(this.value + other.value, this.type)
}

// Helper function to subtract TextUnits
operator fun TextUnit.minus(other: TextUnit): TextUnit {
    if (this.type != other.type)
        throw IllegalArgumentException("Cannot add TextUnits of different types: ${this.type} and ${other.type}")
    return TextUnit(this.value - other.value, this.type)
}
