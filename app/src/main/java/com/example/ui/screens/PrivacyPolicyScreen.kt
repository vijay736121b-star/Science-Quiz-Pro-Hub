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
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.NoAccounts
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AppScreen
import com.example.ui.QuizViewModel
import com.example.ui.components.AppTopBar
import com.example.ui.components.InfoSectionCard
import com.example.ui.theme.NavyBackground
import com.example.ui.theme.TextMuted

@Composable
fun PrivacyPolicyScreen(
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
            title = "Privacy Policy",
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
                    .widthIn(max = 600.dp)
            ) {
                InfoSectionCard(
                    title = "1. Zero Personal Data Collection",
                    body = "Science Quiz Pro does NOT collect, transmit, sell, or share any personal data, identifiers, device IDs, or user analytics. The app operates with complete user anonymity.",
                    icon = Icons.Default.Security
                )

                InfoSectionCard(
                    title = "2. No Account or Login Required",
                    body = "You never need to provide an email address, phone number, name, social account, or payment method. The app is immediately usable upon installation.",
                    icon = Icons.Default.NoAccounts
                )

                InfoSectionCard(
                    title = "3. Offline Local Storage Only",
                    body = "Quiz scores and history are stored exclusively inside a local SQLite/Room database on your device sandbox. No score or performance data is ever uploaded to any external server or cloud service.",
                    icon = Icons.Default.PhoneAndroid
                )

                InfoSectionCard(
                    title = "4. No Device Permissions Required",
                    body = "Science Quiz Pro does not request camera, microphone, contacts, location, storage, or phone state permissions. Only standard system runtime capabilities are used.",
                    icon = Icons.Default.Block
                )

                InfoSectionCard(
                    title = "5. No Advertising or Third-Party Trackers",
                    body = "This application contains zero advertisement SDKs, analytical trackers, profiling beacons, or third-party cookies.",
                    icon = Icons.Default.WifiOff
                )

                InfoSectionCard(
                    title = "6. Educational Integrity & Policy Compliance",
                    body = "Designed in accordance with Google Play Developer Program Policies, COPPA principles, and educational standards, ensuring a safe, wholesome experience for learners of all ages.",
                    icon = Icons.Default.CheckCircle
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Effective Date: September 2026\nScience Quiz Pro • Educational Software",
                    color = TextMuted,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
