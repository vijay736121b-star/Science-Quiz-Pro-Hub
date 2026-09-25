package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.HistoryScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.InstructionsScreen
import com.example.ui.screens.PrivacyPolicyScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.ResultScreen
import com.example.ui.screens.ReviewScreen
import com.example.ui.theme.NavyBackground
import com.example.ui.theme.ScienceQuizProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScienceQuizProTheme {
                val viewModel: QuizViewModel = viewModel()
                val currentScreen by viewModel.currentScreen.collectAsState()

                Scaffold(
                    contentWindowInsets = WindowInsets.safeDrawing,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(NavyBackground)
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (currentScreen) {
                            AppScreen.HOME -> HomeScreen(viewModel = viewModel)
                            AppScreen.QUIZ -> QuizScreen(viewModel = viewModel)
                            AppScreen.RESULT -> ResultScreen(viewModel = viewModel)
                            AppScreen.REVIEW -> ReviewScreen(viewModel = viewModel)
                            AppScreen.INSTRUCTIONS -> InstructionsScreen(viewModel = viewModel)
                            AppScreen.ABOUT -> AboutScreen(viewModel = viewModel)
                            AppScreen.PRIVACY_POLICY -> PrivacyPolicyScreen(viewModel = viewModel)
                            AppScreen.HISTORY -> HistoryScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
