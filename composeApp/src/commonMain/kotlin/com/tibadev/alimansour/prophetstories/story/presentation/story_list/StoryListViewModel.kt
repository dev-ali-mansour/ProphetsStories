package com.tibadev.alimansour.prophetstories.story.presentation.story_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tibadev.alimansour.prophetstories.core.domain.onError
import com.tibadev.alimansour.prophetstories.core.domain.onSuccess
import com.tibadev.alimansour.prophetstories.core.presentation.asString
import com.tibadev.alimansour.prophetstories.story.domain.usecase.GetStories
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class StoryListViewModel(
    private val getStoriesUseCase: GetStories,
) : ViewModel() {
    private val _state = MutableStateFlow(StoryListState())
    val state = _state
        .onStart {
            getStories()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private fun getStories() = viewModelScope.launch {
        _state.update {
            it.copy(isLoading = true)
        }
        getStoriesUseCase()
            .onSuccess { stories ->
            _state.update {
                it.copy(
                    isLoading = false,
                    stories = stories,
                    error = null
                )
            }
        }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        stories = emptyList(),
                        error = error
                    )
                }
            }

    }

}