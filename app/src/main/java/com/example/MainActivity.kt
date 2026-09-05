package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.ui.components.ZynovaBottomNavBar
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.BookmarkScreen
import com.example.ui.screens.ClassSelectionScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LanguageSelectionScreen
import com.example.ui.screens.ProjectDetailsScreen
import com.example.ui.screens.ProjectListScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.Screen
import com.example.viewmodel.ZynovaViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    private val viewModel: ZynovaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                ZynovaApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun ZynovaApp(viewModel: ZynovaViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val isFirstLaunch by viewModel.isFirstLaunch.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.userMessage.collectLatest { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    // Hardware back button behavior
    BackHandler(enabled = uiState.currentScreen !is Screen.Home && uiState.currentScreen !is Screen.Splash) {
        when (uiState.currentScreen) {
            is Screen.LanguageSelect -> if (!isFirstLaunch) viewModel.navigateTo(Screen.Home)
            is Screen.ProjectDetails -> viewModel.navigateTo(Screen.ProjectList)
            is Screen.ProjectList -> viewModel.navigateTo(Screen.Home)
            is Screen.Classes -> viewModel.navigateTo(Screen.Home)
            is Screen.Bookmarks -> viewModel.navigateTo(Screen.Home)
            is Screen.About -> viewModel.navigateTo(Screen.Home)
            else -> viewModel.navigateTo(Screen.Home)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        containerColor = DarkBackground,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            if (uiState.currentScreen !is Screen.Splash &&
                uiState.currentScreen !is Screen.ProjectDetails &&
                uiState.currentScreen !is Screen.LanguageSelect
            ) {
                ZynovaBottomNavBar(
                    currentScreen = uiState.currentScreen,
                    onNavigate = { screen -> viewModel.navigateTo(screen) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = uiState.currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "screen_transition"
            ) { targetScreen ->
                when (targetScreen) {
                    is Screen.Splash -> SplashScreen(
                        onStartClick = {
                            viewModel.navigateTo(Screen.Home)
                        }
                    )
                    is Screen.LanguageSelect -> LanguageSelectionScreen(
                        viewModel = viewModel,
                        onNavigate = { screen -> viewModel.navigateTo(screen) },
                        isFirstLaunch = isFirstLaunch
                    )
                    is Screen.Home -> HomeScreen(
                        viewModel = viewModel,
                        onNavigate = { screen -> viewModel.navigateTo(screen) }
                    )
                    is Screen.Classes -> ClassSelectionScreen(
                        viewModel = viewModel,
                        onNavigate = { screen -> viewModel.navigateTo(screen) }
                    )
                    is Screen.ProjectList -> ProjectListScreen(
                        viewModel = viewModel,
                        onNavigate = { screen -> viewModel.navigateTo(screen) }
                    )
                    is Screen.ProjectDetails -> ProjectDetailsScreen(
                        viewModel = viewModel,
                        onNavigate = { screen -> viewModel.navigateTo(screen) }
                    )
                    is Screen.Bookmarks -> BookmarkScreen(
                        viewModel = viewModel,
                        onNavigate = { screen -> viewModel.navigateTo(screen) }
                    )
                    is Screen.About -> AboutScreen(
                        viewModel = viewModel,
                        onNavigate = { screen -> viewModel.navigateTo(screen) }
                    )
                }
            }
        }
    }
}
