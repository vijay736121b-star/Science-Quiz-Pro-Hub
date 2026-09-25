package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.HighlightOff
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.SciencePrimaryButton
import com.example.ui.components.ScienceSecondaryButton
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
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WarningAmber
import com.example.ui.theme.WarningAmberBg
import java.util.Locale

@Composable
fun ResultScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val record = uiState.latestRecord
    val scrollState = rememberScrollState()

    BackHandler {
        viewModel.navigateTo(AppScreen.HOME)
    }

    val score = record?.score ?: 0
    val total = record?.totalQuestions ?: 51
    val percentage = record?.percentage ?: 0f
    val grade = record?.grade ?: "N/A"
    val correct = record?.correctCount ?: 0
    val incorrect = record?.incorrectCount ?: 0
    val unanswered = record?.unansweredCount ?: 0

    val encouragingMessage = when {
        percentage >= 95f -> "Extraordinary! You have mastered these scientific principles with top honors!"
        percentage >= 90f -> "Outstanding achievement! You possess an exceptional scientific mind!"
        percentage >= 80f -> "Great job! You demonstrated a strong grasp of diverse scientific concepts."
        percentage >= 70f -> "Good effort! With just a bit more revision, you will achieve top mastery."
        percentage >= 55f -> "Fair attempt! Review the explanations to deepen your scientific understanding."
        else -> "Keep learning! Science is an exciting journey of curiosity, discovery, and perseverance."
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NavyBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .widthIn(max = 600.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Trophy / Trophy badge
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(EmeraldGreenContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = "Quiz Completed",
                    tint = EmeraldGreenText,
                    modifier = Modifier.size(44.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Header Title
            Text(
                text = "Quiz Finished!",
                color = TextPrimary,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("result_header_title")
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "51 Science Questions Completed",
                color = TextSecondary,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Main Score & Grade Card
            Card(
                colors = CardDefaults.cardColors(containerColor = NavyCard),
                border = BorderStroke(1.5.dp, EmeraldGreen),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("score_summary_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "TOTAL SCORE",
                        color = TextSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$score",
                            color = EmeraldGreen,
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = " / $total",
                            color = TextSecondary,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Percentage Pill
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(NavyBackground)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = String.format(Locale.US, "%.1f%%", percentage),
                                color = CyanAccent,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Grade Pill
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(EmeraldGreenContainer)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Grade: $grade",
                                color = EmeraldGreenText,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Encouraging message
                    Text(
                        text = encouragingMessage,
                        color = TextPrimary,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(NavyBackground.copy(alpha = 0.6f))
                            .padding(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Breakdown Grid (Correct, Incorrect, Unanswered)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Correct Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = CorrectGreenBg),
                    border = BorderStroke(1.dp, CorrectGreen),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = CorrectGreen,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$correct",
                            color = TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Correct",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }

                // Incorrect Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = IncorrectRedBg),
                    border = BorderStroke(1.dp, IncorrectRed),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.HighlightOff,
                            contentDescription = null,
                            tint = IncorrectRed,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$incorrect",
                            color = TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Incorrect",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }

                // Unanswered Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = WarningAmberBg),
                    border = BorderStroke(1.dp, WarningAmber),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.HelpOutline,
                            contentDescription = null,
                            tint = WarningAmber,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$unanswered",
                            color = TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Unanswered",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action: Restart Quiz (Prominent Emerald Green)
            SciencePrimaryButton(
                text = "Restart Quiz",
                onClick = { viewModel.startNewQuiz() },
                icon = Icons.Default.Refresh,
                testTag = "restart_quiz_button"
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Action: Review Answers
            ScienceSecondaryButton(
                text = "Review All Answers & Explanations",
                onClick = { viewModel.navigateTo(AppScreen.REVIEW) },
                icon = Icons.Default.RateReview,
                testTag = "review_answers_button"
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Action: Return Home
            ScienceSecondaryButton(
                text = "Return Home",
                onClick = { viewModel.navigateTo(AppScreen.HOME) },
                icon = Icons.Default.Home,
                testTag = "result_home_button"
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
