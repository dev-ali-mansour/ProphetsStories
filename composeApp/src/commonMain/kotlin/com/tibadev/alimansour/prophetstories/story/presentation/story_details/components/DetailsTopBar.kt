package com.tibadev.alimansour.prophetstories.story.presentation.story_details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.tibadev.alimansour.prophetstories.core.presentation.theme.barBackgroundColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.helveticaFamily
import com.tibadev.alimansour.prophetstories.core.util.Strings


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
                fontFamily = helveticaFamily()
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
                    contentDescription = Strings.about_us,
                )
            }
        },
        actions = {
            Row {
                IconButton(onClick = onZoomOutClicked) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = Strings.size_down,
                    )
                }
                IconButton(onClick = onZoomInClicked) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = Strings.size_up,
                    )
                }
            }
        })
}