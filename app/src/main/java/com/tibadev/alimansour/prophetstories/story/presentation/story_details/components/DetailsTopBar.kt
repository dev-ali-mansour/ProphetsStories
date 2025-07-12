package com.tibadev.alimansour.prophetstories.story.presentation.story_details.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.core.presentation.theme.barBackgroundColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.helveticaFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBackClick: () -> Unit,
    onZoomInClicked: () -> Unit,
    onZoomOutClicked: () -> Unit,
    title: String = "",
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontFamily = helveticaFamily
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = barBackgroundColor,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onZoomInClicked) {
                Icon(
                    painter = painterResource(R.drawable.ic_action_size_up),
                    contentDescription = null,
                )
            }
            IconButton(onClick = onZoomOutClicked) {
                Icon(
                    painter = painterResource(R.drawable.ic_action_size_down),
                    contentDescription = null,
                )
            }
        })
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopBarPreview() {
    DetailsTopBar(
        title = "",
        onZoomInClicked = { },
        onZoomOutClicked = { },
        onBackClick = {},
    )
}