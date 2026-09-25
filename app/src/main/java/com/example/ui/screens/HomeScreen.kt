package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Timer
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.SciencePrimaryButton
import com.example.ui.components.ScienceSecondaryButton
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.EmeraldGreenContainer
import com.example.ui.theme.NavyBackground
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardBorder
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WarningAmber

@Composable
fun HomeScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val bestRecord by viewModel.bestRecord.collectAsState()
    val historyList by viewModel.allHistory.collectAsState()

    val scrollState = rememberScrollState()

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

            // Logo Header with glowing border
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .shadow(12.dp, CircleShape, spotColor = EmeraldGreen)
                    .clip(CircleShape)
                    .background(NavyCard)
                    .border(2.5.dp, EmeraldGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_science_quiz_logo),
                    contentDescription = "Science Quiz Pro Logo",
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "Science Quiz Pro",
                color = TextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                letterSpacing = 0.5.sp,
                modifier = Modifier.testTag("home_title")
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Tagline
            Text(
                text = "Learn Science. Test Your Knowledge.",
                color = EmeraldGreen,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(22.dp))

            // Stats Card
            Card(
                colors = CardDefaults.cardColors(containerColor = NavyCard),
                border = BorderStroke(1.dp, NavyCardBorder),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("home_stats_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Science,
                                contentDescription = null,
                                tint = CyanAccent,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "51",
                                color = TextPrimary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "Questions",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(36.dp)
                            .background(NavyCardBorder)
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = null,
                                tint = WarningAmber,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (bestRecord != null) "${bestRecord?.score}/51" else "—",
                                color = TextPrimary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = if (bestRecord != null) "Best (${bestRecord?.percentage?.toInt()}%)" else "Best Score",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(36.dp)
                            .background(NavyCardBorder)
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = null,
                                tint = EmeraldGreen,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${historyList.size}",
                                color = TextPrimary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "Completed",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Timer Configuration Card (Requirement 18: configurable question timer, default 10s)
            Card(
                colors = CardDefaults.cardColors(containerColor = NavyCard),
                border = BorderStroke(1.dp, NavyCardBorder),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("timer_config_card")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = null,
                            tint = EmeraldGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Question Timer",
                            color = TextPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = if (uiState.timerDurationSeconds > 0) "${uiState.timerDurationSeconds}s per question" else "Untimed",
                            color = EmeraldGreen,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Timer option chips
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val timerOptions = listOf(10 to "10s (Def)", 15 to "15s", 20 to "20s", 30 to "30s", 0 to "Off")
                        timerOptions.forEach { (seconds, label) ->
                            val isSelected = uiState.timerDurationSeconds == seconds
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if (isSelected) EmeraldGreen else NavyBackground
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = if (isSelected) EmeraldGreen else NavyCardBorder,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable { viewModel.setTimerDuration(seconds) }
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) NavyBackground else TextSecondary,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Primary: Start Quiz Button
            SciencePrimaryButton(
                text = "Start Quiz",
                onClick = { viewModel.startNewQuiz() },
                icon = Icons.Default.PlayArrow,
                testTag = "start_quiz_button"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Instructions Button
            ScienceSecondaryButton(
                text = "Instructions",
                onClick = { viewModel.navigateTo(AppScreen.INSTRUCTIONS) },
                icon = Icons.Default.MenuBook,
                testTag = "instructions_button"
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Past Results Button (if history exists)
            if (historyList.isNotEmpty()) {
                ScienceSecondaryButton(
                    text = "Past Results History",
                    onClick = { viewModel.navigateTo(AppScreen.HISTORY) },
                    icon = Icons.Default.History,
                    testTag = "history_button"
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            // About Button
            ScienceSecondaryButton(
                text = "About",
                onClick = { viewModel.navigateTo(AppScreen.ABOUT) },
                icon = Icons.Default.Info,
                testTag = "about_button"
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Privacy Policy Button
            ScienceSecondaryButton(
                text = "Privacy Policy",
                onClick = { viewModel.navigateTo(AppScreen.PRIVACY_POLICY) },
                icon = Icons.Default.Policy,
                testTag = "privacy_policy_button"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Footer note
            Text(
                text = "100% Offline • No Account Required • 51 Questions",
                color = TextMuted,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
