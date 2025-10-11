package com.tibadev.alimansour.prophetstories.story.presentation.story_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.tibadev.alimansour.prophetstories.app.Route
import com.tibadev.alimansour.prophetstories.core.domain.onError
import com.tibadev.alimansour.prophetstories.core.domain.onSuccess
import com.tibadev.alimansour.prophetstories.core.presentation.getMessage
import com.tibadev.alimansour.prophetstories.story.domain.usecase.GetStoryDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StoryDetailsViewModel(
    private val getStoryDetailsUseCase: GetStoryDetails,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val prophet = savedStateHandle.toRoute<Route.StoryDetails>().prophet

    private val _state = MutableStateFlow(StoryDetailsState())
    val state = _state
        .onStart {
            getStoryDetails()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: StoryDetailsAction) {
        when (action) {
            is StoryDetailsAction.OnSelectedStoryChange -> {
                _state.update {
                    it.copy(
                        story = action.story,
                    )
                }
            }

            else -> Unit
        }
    }

    private fun getStoryDetails() {
        viewModelScope.launch {
            getStoryDetailsUseCase(prophet)
                .onSuccess { story ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        story = story,
                        error = null
                    )
                }
                .onError { error ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        story = null,
                        error = error.getMessage()
                    )
                }

        }
    }
}