package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.QuizViewModel
import com.example.ui.theme.CorrectGreen
import com.example.ui.theme.CorrectGreenBg
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.EmeraldGreenContainer
import com.example.ui.theme.EmeraldGreenText
import com.example.ui.theme.IncorrectRed
import com.example.ui.theme.IncorrectRedBg
import com.example.ui.theme.NavyBackground
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardBorder
import com.example.ui.theme.NavyCardSelected
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WarningAmber
import com.example.ui.theme.WarningAmberBg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    // Handle Android system back button by showing exit confirmation
    BackHandler {
        viewModel.requestExitQuiz()
    }

    val totalQuestions = uiState.questions.size
    val currentIndex = uiState.currentIndex
    val currentQuestion = uiState.questions.getOrNull(currentIndex)
    val currentAnswer = currentQuestion?.let { uiState.userAnswers[it.id] }
    val isAnswered = currentAnswer != null

    val progress = if (totalQuestions > 0) (currentIndex + 1).toFloat() / totalQuestions.toFloat() else 0f
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "quiz_progress")

    // Timer color: turns amber when <= 4s, red when <= 2s
    val timerColor by animateColorAsState(
        targetValue = when {
            uiState.timerSecondsRemaining <= 2 -> IncorrectRed
            uiState.timerSecondsRemaining <= 4 -> WarningAmber
            else -> EmeraldGreen
        },
        label = "timer_color"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NavyBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp)
                .align(Alignment.TopCenter)
        ) {
            // Header Bar
            Surface(
                color = NavyBackground,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Home Button
                    IconButton(
                        onClick = { viewModel.requestExitQuiz() },
                        modifier = Modifier
                            .size(44.dp)
                            .testTag("quiz_home_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Return Home",
                            tint = TextSecondary
                        )
                    }

                    // Question counter
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Question ${currentIndex + 1} / $totalQuestions",
                            color = TextPrimary,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.testTag("question_counter_text")
                        )
                        if (currentQuestion != null) {
                            Text(
                                text = currentQuestion.category,
                                color = CyanAccent,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    // Exit Quiz Button
                    IconButton(
                        onClick = { viewModel.requestExitQuiz() },
                        modifier = Modifier
                            .size(44.dp)
                            .testTag("quiz_exit_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Exit Quiz",
                            tint = IncorrectRed
                        )
                    }
                }
            }

            // Progress Bar
            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .testTag("quiz_progress_bar"),
                color = EmeraldGreen,
                trackColor = NavyCardBorder,
            )

            // Timer Bar (if timer enabled)
            if (uiState.timerDurationSeconds > 0) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(NavyCard)
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = "Timer",
                            tint = timerColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isAnswered) {
                                if (currentAnswer.isTimedOut) "Time Expired" else "Answered"
                            } else {
                                "Time remaining: ${uiState.timerSecondsRemaining}s"
                            },
                            color = if (isAnswered) TextMuted else timerColor,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.testTag("timer_text")
                        )
                    }
                }
            }

            // Scrollable Question & Options Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                if (currentQuestion != null) {
                    // Question Card
                    Card(
                        colors = CardDefaults.cardColors(containerColor = NavyCard),
                        border = BorderStroke(1.dp, NavyCardBorder),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("question_text_card")
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(EmeraldGreenContainer)
                                        .padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Text(
                                        text = currentQuestion.category.uppercase(),
                                        color = EmeraldGreenText,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 0.5.sp
                                    )
                                }

                                if (isAnswered) {
                                    if (currentAnswer.isCorrect) {
                                        Text(
                                            text = "✓ Correct (+1)",
                                            color = CorrectGreen,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    } else if (currentAnswer.isTimedOut) {
                                        Text(
                                            text = "⏱ Timed Out (0)",
                                            color = WarningAmber,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    } else {
                                        Text(
                                            text = "✗ Incorrect (0)",
                                            color = IncorrectRed,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = currentQuestion.questionText,
                                color = TextPrimary,
                                fontSize = 19.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 26.sp,
                                modifier = Modifier.testTag("question_text")
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 4 Answer Option Buttons (Letters A, B, C, D)
                    val optionLetters = listOf("A", "B", "C", "D")
                    currentQuestion.options.forEachIndexed { index, optionText ->
                        val letter = optionLetters.getOrElse(index) { "${index + 1}" }
                        val isOptionCorrect = index == currentQuestion.correctOptionIndex
                        val isUserSelection = currentAnswer?.selectedOptionIndex == index

                        val cardBg: Color
                        val cardBorder: Color
                        val badgeBg: Color
                        val badgeTextColor: Color
                        val textColor: Color

                        if (isAnswered) {
                            when {
                                isOptionCorrect -> {
                                    // Correct answer always highlighted green once answered
                                    cardBg = CorrectGreenBg
                                    cardBorder = CorrectGreen
                                    badgeBg = CorrectGreen
                                    badgeTextColor = NavyBackground
                                    textColor = TextPrimary
                                }
                                isUserSelection -> {
                                    // User picked this wrong option: highlight red
                                    cardBg = IncorrectRedBg
                                    cardBorder = IncorrectRed
                                    badgeBg = IncorrectRed
                                    badgeTextColor = TextPrimary
                                    textColor = TextPrimary
                                }
                                else -> {
                                    // Other options dimmed
                                    cardBg = NavyCard.copy(alpha = 0.5f)
                                    cardBorder = NavyCardBorder.copy(alpha = 0.5f)
                                    badgeBg = NavyCardBorder
                                    badgeTextColor = TextMuted
                                    textColor = TextSecondary.copy(alpha = 0.6f)
                                }
                            }
                        } else {
                            // Unanswered state
                            cardBg = NavyCard
                            cardBorder = NavyCardBorder
                            badgeBg = NavyBackground
                            badgeTextColor = EmeraldGreen
                            textColor = TextPrimary
                        }

                        Card(
                            colors = CardDefaults.cardColors(containerColor = cardBg),
                            border = BorderStroke(
                                if (isAnswered && (isOptionCorrect || isUserSelection)) 1.8.dp else 1.dp,
                                cardBorder
                            ),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .clickable(
                                    enabled = !isAnswered,
                                    onClick = { viewModel.selectOption(index) }
                                )
                                .testTag("option_button_$index")
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Option Letter Badge
                                Box(
                                    modifier = Modifier
                                        .size(34.dp)
                                        .clip(CircleShape)
                                        .background(badgeBg),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isAnswered && isOptionCorrect) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "Correct",
                                            tint = NavyBackground,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    } else if (isAnswered && isUserSelection && !currentAnswer.isCorrect) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Incorrect",
                                            tint = TextPrimary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                    } else {
                                        Text(
                                            text = letter,
                                            color = badgeTextColor,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    text = optionText,
                                    color = textColor,
                                    fontSize = 16.sp,
                                    fontWeight = if (isOptionCorrect && isAnswered) FontWeight.Bold else FontWeight.Medium,
                                    modifier = Modifier.weight(1f)
                                )

                                if (isAnswered) {
                                    if (isOptionCorrect) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(CorrectGreen)
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = "Correct",
                                                color = NavyBackground,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    } else if (isUserSelection) {
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(IncorrectRed)
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = "Your Choice",
                                                color = TextPrimary,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Educational Explanation Card (immediately displayed after answering or timing out)
                    AnimatedVisibility(
                        visible = isAnswered,
                        enter = fadeIn() + slideInVertically()
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = if (currentAnswer?.isCorrect == true) CorrectGreenBg.copy(alpha = 0.6f)
                                else if (currentAnswer?.isTimedOut == true) WarningAmberBg.copy(alpha = 0.6f)
                                else IncorrectRedBg.copy(alpha = 0.5f)
                            ),
                            border = BorderStroke(
                                1.dp,
                                if (currentAnswer?.isCorrect == true) CorrectGreen
                                else if (currentAnswer?.isTimedOut == true) WarningAmber
                                else IncorrectRed
                            ),
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .testTag("explanation_card")
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = null,
                                        tint = if (currentAnswer?.isCorrect == true) CorrectGreen
                                        else if (currentAnswer?.isTimedOut == true) WarningAmber
                                        else IncorrectRed,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (currentAnswer?.isCorrect == true) "Scientific Explanation"
                                        else if (currentAnswer?.isTimedOut == true) "Time's Up! Explanation"
                                        else "Explanation & Correct Answer",
                                        color = TextPrimary,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = currentQuestion.explanation,
                                    color = TextPrimary.copy(alpha = 0.95f),
                                    fontSize = 14.sp,
                                    lineHeight = 21.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            // Bottom Navigation Bar (Previous & Next/Finish)
            Surface(
                color = NavyCard,
                tonalElevation = 4.dp,
                border = BorderStroke(1.dp, NavyCardBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Previous Button
                    OutlinedButton(
                        onClick = { viewModel.moveToPreviousQuestion() },
                        enabled = currentIndex > 0,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = NavyBackground,
                            contentColor = TextPrimary,
                            disabledContainerColor = NavyBackground.copy(alpha = 0.4f),
                            disabledContentColor = TextMuted
                        ),
                        border = BorderStroke(1.dp, if (currentIndex > 0) NavyCardBorder else NavyCardBorder.copy(alpha = 0.3f)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("quiz_previous_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Previous",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    // Next / Finish Button
                    val isLastQuestion = currentIndex == totalQuestions - 1
                    Button(
                        onClick = { viewModel.moveToNextQuestion() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EmeraldGreen,
                            contentColor = NavyBackground
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("quiz_next_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isLastQuestion) "Finish Quiz" else "Next",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }

        // Exit Confirmation Dialog
        if (uiState.showExitConfirmDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.dismissExitDialog() },
                title = {
                    Text(
                        text = "Exit Science Quiz?",
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text(
                        text = "Your current quiz progress will be lost if you leave now. Are you sure you want to return to Home?",
                        color = TextSecondary,
                        fontSize = 14.sp
                    )
                },
                containerColor = NavyCard,
                confirmButton = {
                    Button(
                        onClick = { viewModel.confirmExitQuiz() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = IncorrectRed,
                            contentColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.testTag("confirm_exit_button")
                    ) {
                        Text("Exit Quiz", fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { viewModel.dismissExitDialog() },
                        modifier = Modifier.testTag("cancel_exit_button")
                    ) {
                        Text("Continue Quiz", color = EmeraldGreen, fontWeight = FontWeight.SemiBold)
                    }
                }
            )
        }
    }
}
