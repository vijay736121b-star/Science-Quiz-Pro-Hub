package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.AppTopBar
import com.example.ui.components.InfoSectionCard
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EmeraldGreen
import com.example.ui.theme.NavyBackground
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardBorder
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun AboutScreen(
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
            title = "About",
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
                    .padding(horizontal = 20.dp, vertical = 16.dp)
                    .widthIn(max = 600.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo & Version
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(NavyCard)
                        .border(2.dp, EmeraldGreen, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_science_quiz_logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Science Quiz Pro",
                    color = TextPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Version 1.0 • Educational Edition",
                    color = EmeraldGreen,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(20.dp))

                InfoSectionCard(
                    title = "Purpose & Mission",
                    body = "Science Quiz Pro was designed for students, educators, and science enthusiasts seeking a lightweight, distraction-free environment to test and expand their scientific literacy.",
                    icon = Icons.Default.School
                )

                InfoSectionCard(
                    title = "Scientific Domains Covered",
                    body = "• Biology: Cell biology, human anatomy, genetics, botany\n• Chemistry: Elements, periodic table, chemical bonds, reactions\n• Physics: Classical mechanics, thermodynamics, optics, electromagnetism\n• Astronomy: Solar system, stars, galaxies, cosmology\n• Earth Science: Geology, atmospheric science, magnetosphere",
                    icon = Icons.Default.Science
                )

                InfoSectionCard(
                    title = "Lightweight & High Performance",
                    body = "Engineered natively using Kotlin and Jetpack Compose without heavy third-party tracking frameworks, ensuring instant load times and smooth operation on any Android smartphone or tablet.",
                    icon = Icons.Default.Speed
                )

                InfoSectionCard(
                    title = "Completely Offline & Private",
                    body = "All 51 questions and explanation databases are embedded locally. The app requires zero permissions and respects your complete privacy.",
                    icon = Icons.Default.Security
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Crafted with Kotlin & Jetpack Compose\nDesigned for students and curious minds everywhere.",
                    color = TextMuted,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
