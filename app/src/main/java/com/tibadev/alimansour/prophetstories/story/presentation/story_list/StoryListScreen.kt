package com.tibadev.alimansour.prophetstories.story.presentation.story_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.core.presentation.theme.S_PADDING
import com.tibadev.alimansour.prophetstories.core.presentation.theme.screenBackgroundColor
import com.tibadev.alimansour.prophetstories.story.domain.model.Story
import com.tibadev.alimansour.prophetstories.story.presentation.story_list.components.StoriesTopBar
import com.tibadev.alimansour.prophetstories.story.presentation.story_list.components.StoryListItem
import org.koin.androidx.compose.koinViewModel


@Composable
fun StoryListScreen(
    viewModel: StoryListViewModel = koinViewModel(),
    onSettingsClicked: () -> Unit,
    onStoryClicked: (story: Story) -> Unit,
) {
    val listState = rememberLazyListState()
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            StoriesTopBar(
                title = stringResource(R.string.app_name),
                onSettingsClicked = {
                    onSettingsClicked()
                })
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = screenBackgroundColor)
                .padding(paddingValues)
        ) {
            StoryListContent(
                listState = listState,
                state = state,
                onAction = { action ->
                    when (action) {
                        is StoryListAction.OnStoryClick -> onStoryClicked(action.story)
                    }
                }
            )
        }
    }
}

@Composable
fun StoryListContent(
    listState: LazyListState,
    state: StoryListState,
    onAction: (StoryListAction) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> {
                Text(
                    text = stringResource(state.error),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            state.stories.isEmpty() -> {
                Text(
                    text = stringResource(R.string.no_stories),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = listState,
                    contentPadding = PaddingValues(all = S_PADDING),
                    verticalArrangement = Arrangement.spacedBy(S_PADDING)
                ) {
                    items(state.stories) { story ->
                        StoryListItem(
                            text = story.prophet,
                            modifier = Modifier.clickable {
                                onAction(StoryListAction.OnStoryClick(story))
                            },
                        )
                    }
                }
            }
        }
    }
}
