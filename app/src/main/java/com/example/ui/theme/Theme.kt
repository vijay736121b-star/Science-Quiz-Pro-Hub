package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val ScienceQuizColorScheme = darkColorScheme(
  primary = EmeraldGreen,
  onPrimary = NavyBackground,
  primaryContainer = EmeraldGreenContainer,
  onPrimaryContainer = EmeraldGreenText,
  secondary = CyanAccent,
  onSecondary = NavyBackground,
  secondaryContainer = NavyCard,
  onSecondaryContainer = CyanAccentLight,
  tertiary = EmeraldGreenLight,
  onTertiary = NavyBackground,
  background = NavyBackground,
  onBackground = TextPrimary,
  surface = NavySurface,
  onSurface = TextPrimary,
  surfaceVariant = NavyCard,
  onSurfaceVariant = TextSecondary,
  outline = NavyCardBorder,
  error = IncorrectRed,
  onError = White,
  errorContainer = IncorrectRedBg,
  onErrorContainer = IncorrectRedLight
)

@Composable
fun ScienceQuizProTheme(
  content: @Composable () -> Unit
) {
  // Science Quiz Pro has a dedicated Dark Navy & Green branded theme
  MaterialTheme(
    colorScheme = ScienceQuizColorScheme,
    typography = Typography,
    content = content
  )
}
