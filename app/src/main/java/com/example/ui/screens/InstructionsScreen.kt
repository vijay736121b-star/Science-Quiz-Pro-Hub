package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Grade
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.AppTopBar
import com.example.ui.components.InfoSectionCard
import com.example.ui.components.SciencePrimaryButton
import com.example.ui.theme.NavyBackground

@Composable
fun InstructionsScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    BackHandler {
        viewModel.navigateTo(AppScreen.HOME)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NavyBackground)
    ) {
        AppTopBar(
            title = "Instructions",
            onBackClick = { viewModel.navigateTo(AppScreen.HOME) }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .widthIn(max = 600.dp)
            ) {
                InfoSectionCard(
                    title = "1. 51 Science Questions",
                    body = "Each quiz presents 51 multiple-choice questions curated across Biology, Chemistry, Physics, Astronomy, and Earth Science. Questions and choices are randomized so every quiz experience is fresh.",
                    icon = Icons.Default.FormatListNumbered
                )

                InfoSectionCard(
                    title = "2. Configurable 10s Question Timer",
                    body = "By default, each question gives you 10 seconds to answer. You can configure the timer to 10s, 15s, 20s, 30s, or Off on the Home screen. When the timer expires, the question automatically records as unanswered and moves to the next.",
                    icon = Icons.Default.Timer
                )

                InfoSectionCard(
                    title = "3. Instant Feedback & Explanations",
                    body = "As soon as you tap an option, you will immediately see whether your choice was correct (green) or incorrect (red), alongside a concise educational explanation to reinforce learning.",
                    icon = Icons.Default.Lightbulb
                )

                InfoSectionCard(
                    title = "4. Previous & Next Navigation",
                    body = "Use the Previous and Next buttons at the bottom to navigate between questions. You can review previously answered questions at any time during the session. Double-tapping is prevented to preserve accurate scoring.",
                    icon = Icons.Default.Navigation
                )

                InfoSectionCard(
                    title = "5. Scoring & Grades",
                    body = "Each correct answer awards 1 point (maximum 51). At the end, you receive an overall score, percentage, letter grade (A+, A, B, C, D), and an in-depth breakdown of correct, incorrect, and unanswered questions.",
                    icon = Icons.Default.Grade
                )

                InfoSectionCard(
                    title = "6. 100% Offline & Private",
                    body = "All questions, answer logic, and history records exist entirely on your device. No internet connection or account login is required.",
                    icon = Icons.Default.CheckCircleOutline
                )

                Spacer(modifier = Modifier.height(16.dp))

                SciencePrimaryButton(
                    text = "Start Quiz Now",
                    onClick = { viewModel.startNewQuiz() },
                    icon = Icons.Default.PlayArrow,
                    testTag = "instructions_start_quiz_button"
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
