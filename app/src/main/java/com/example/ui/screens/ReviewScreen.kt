package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Question
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.AppTopBar
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

@Composable
fun ReviewScreen(
    viewModel: QuizViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedFilterIndex by remember { mutableIntStateOf(0) }

    BackHandler {
        viewModel.navigateTo(AppScreen.RESULT)
    }

    val allQuestions = uiState.questions
    val userAnswers = uiState.userAnswers

    val filteredQuestions = when (selectedFilterIndex) {
        1 -> allQuestions.filter { userAnswers[it.id]?.isCorrect == true }
        2 -> allQuestions.filter {
            val ans = userAnswers[it.id]
            ans != null && !ans.isCorrect && ans.isAnswered
        }
        3 -> allQuestions.filter {
            val ans = userAnswers[it.id]
            ans == null || (!ans.isAnswered && ans.isTimedOut) || (!ans.isAnswered && ans.selectedOptionIndex == null)
        }
        else -> allQuestions
    }

    val filterTitles = listOf(
        "All (${allQuestions.size})",
        "Correct (${allQuestions.count { userAnswers[it.id]?.isCorrect == true }})",
        "Incorrect (${allQuestions.count { val ans = userAnswers[it.id]; ans != null && !ans.isCorrect && ans.isAnswered }})",
        "Unanswered (${allQuestions.count { val ans = userAnswers[it.id]; ans == null || (!ans.isAnswered) }})"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NavyBackground)
    ) {
        AppTopBar(
            title = "Review Questions",
            onBackClick = { viewModel.navigateTo(AppScreen.RESULT) }
        )

        // Filter tabs
        ScrollableTabRow(
            selectedTabIndex = selectedFilterIndex,
            containerColor = NavyCard,
            contentColor = EmeraldGreen,
            edgePadding = 12.dp
        ) {
            filterTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedFilterIndex == index,
                    onClick = { selectedFilterIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedFilterIndex == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedFilterIndex == index) EmeraldGreen else TextSecondary,
                            fontSize = 13.sp
                        )
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 680.dp)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(filteredQuestions) { index, question ->
                    val answer = userAnswers[question.id]
                    ReviewQuestionCard(
                        question = question,
                        questionNumber = index + 1,
                        selectedOptionIndex = answer?.selectedOptionIndex,
                        isCorrect = answer?.isCorrect == true,
                        isTimedOut = answer?.isTimedOut == true,
                        isAnswered = answer?.isAnswered == true
                    )
                }
            }
        }
    }
}

@Composable
private fun ReviewQuestionCard(
    question: Question,
    questionNumber: Int,
    selectedOptionIndex: Int?,
    isCorrect: Boolean,
    isTimedOut: Boolean,
    isAnswered: Boolean
) {
    val statusColor: Color
    val statusText: String
    val statusBg: Color

    when {
        isCorrect -> {
            statusColor = CorrectGreen
            statusText = "Correct"
            statusBg = CorrectGreenBg
        }
        isTimedOut -> {
            statusColor = WarningAmber
            statusText = "Timed Out"
            statusBg = WarningAmberBg
        }
        isAnswered -> {
            statusColor = IncorrectRed
            statusText = "Incorrect"
            statusBg = IncorrectRedBg
        }
        else -> {
            statusColor = TextMuted
            statusText = "Skipped"
            statusBg = NavyBackground
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = NavyCard),
        border = BorderStroke(1.dp, statusColor.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Q$questionNumber",
                        color = CyanAccent,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(EmeraldGreenContainer)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = question.category,
                            color = EmeraldGreenText,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(statusBg)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = statusText,
                        color = statusColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Question Text
            Text(
                text = question.questionText,
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Options summary
            question.options.forEachIndexed { optIdx, optText ->
                val isCorrectOpt = optIdx == question.correctOptionIndex
                val isUserOpt = optIdx == selectedOptionIndex

                val optBg = when {
                    isCorrectOpt -> CorrectGreenBg.copy(alpha = 0.7f)
                    isUserOpt -> IncorrectRedBg.copy(alpha = 0.7f)
                    else -> NavyBackground.copy(alpha = 0.4f)
                }
                val optBorder = when {
                    isCorrectOpt -> CorrectGreen
                    isUserOpt -> IncorrectRed
                    else -> NavyCardBorder
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(optBg)
                        .border(1.dp, optBorder, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isCorrectOpt) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Correct",
                            tint = CorrectGreen,
                            modifier = Modifier.size(16.dp)
                        )
                    } else if (isUserOpt) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Your choice",
                            tint = IncorrectRed,
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        Box(modifier = Modifier.size(16.dp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = optText,
                        color = if (isCorrectOpt) TextPrimary else TextSecondary,
                        fontSize = 13.sp,
                        fontWeight = if (isCorrectOpt || isUserOpt) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.weight(1f)
                    )
                    if (isCorrectOpt) {
                        Text(
                            text = "(Correct Answer)",
                            color = CorrectGreen,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    } else if (isUserOpt) {
                        Text(
                            text = "(Your Choice)",
                            color = IncorrectRed,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Explanation
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(NavyBackground)
                    .border(1.dp, NavyCardBorder, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Column {
                    Text(
                        text = "Scientific Explanation:",
                        color = EmeraldGreen,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = question.explanation,
                        color = TextSecondary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
