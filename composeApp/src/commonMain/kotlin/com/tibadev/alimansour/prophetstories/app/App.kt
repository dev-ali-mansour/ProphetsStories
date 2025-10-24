package com.tibadev.alimansour.prophetstories.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.tibadev.alimansour.prophetstories.story.presentation.SelectedStoryViewModel
import com.tibadev.alimansour.prophetstories.story.presentation.about.AboutScreen
import com.tibadev.alimansour.prophetstories.story.presentation.settings.SettingsViewModel
import com.tibadev.alimansour.prophetstories.story.presentation.splash.SplashScreen
import com.tibadev.alimansour.prophetstories.story.presentation.story_details.StoryDetailsAction
import com.tibadev.alimansour.prophetstories.story.presentation.story_details.StoryDetailsScreen
import com.tibadev.alimansour.prophetstories.story.presentation.story_details.StoryDetailsViewModel
import com.tibadev.alimansour.prophetstories.story.presentation.story_list.StoryListScreen
import com.tibadev.alimansour.prophetstories.story.presentation.story_list.StoryListViewModel
import com.tibadev.alimansour.prophetstories.story.presentation.settings.SettingsScreen
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.StoryGraph
    ) {
        navigation<Route.StoryGraph>(startDestination = Route.StoryList) {
            composable<Route.Splash> {
                SplashScreen(navController = navController)
            }

            composable<Route.StoryList> {
                val viewModel = koinViewModel<StoryListViewModel>()
                val selectedStoryViewModel =
                    it.sharedKoinViewModel<SelectedStoryViewModel>(navController)

                LaunchedEffect(true) {
                    selectedStoryViewModel.onSelectStory(null)
                }
                StoryListScreen(
                    viewModel = viewModel,
                    onSettingsClicked = {
                        navController.navigate(Route.Settings)
                    },
                    onStoryClicked = { story ->
                        selectedStoryViewModel.onSelectStory(story)
                        navController.navigate(
                            Route.StoryDetails(
                                story.prophet
                            )
                        )
                    }
                )
            }
            composable<Route.StoryDetails> {
                val selectedStoryViewModel =
                    it.sharedKoinViewModel<SelectedStoryViewModel>(navController)
                val viewModel = koinViewModel<StoryDetailsViewModel>()
                val selectedStory by selectedStoryViewModel.selectedStory.collectAsStateWithLifecycle()

                LaunchedEffect(selectedStory) {
                    selectedStory?.let { story ->
                        viewModel.onAction(StoryDetailsAction.OnSelectedStoryChange(story))
                    }
                }
                StoryDetailsScreen(viewModel = viewModel, onBackClick = {
                    navController.navigateUp()
                })
            }
            composable<Route.Settings> {
                val viewModel = koinViewModel<SettingsViewModel>()

                SettingsScreen(
                    viewModel = viewModel,
                    onBackClicked = {
                        navController.navigateUp()
                    },
                    navigateTo = { route ->
                        navController.navigate(route)
                    }
                )
            }
            composable<Route.About> {
                AboutScreen(
                    onBackClicked = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}


@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}
