package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Neon Dark Palette for ZYNOVA
val DarkBackground = Color(0xFF07090E)
val DarkSurface = Color(0xFF0F1422)
val DarkSurfaceCard = Color(0xFF141B2D)
val DarkSurfaceVariant = Color(0xFF1C243B)
val DarkBorder = Color(0xFF263352)
val DarkBorderGlow = Color(0xFF384B75)

// Neon Accents
val NeonPurple = Color(0xFF8B5CF6)
val NeonPurpleLight = Color(0xFFA78BFA)
val NeonPurpleDark = Color(0xFF6D28D9)
val NeonCyan = Color(0xFF06B6D4)
val NeonCyanLight = Color(0xFF38BDF8)
val NeonBlue = Color(0xFF3B82F6)
val NeonPink = Color(0xFFEC4899)
val NeonGreen = Color(0xFF10B981)
val NeonAmber = Color(0xFFF59E0B)
val NeonCoral = Color(0xFFF43F5E)

// Text Colors
val TextWhite = Color(0xFFF8FAFC)
val TextMuted = Color(0xFF94A3B8)
val TextDark = Color(0xFF64748B)

// Gradients
val PurpleCyanGradient = Brush.horizontalGradient(
    listOf(Color(0xFF8B5CF6), Color(0xFF06B6D4))
)

val PurpleBlueGradient = Brush.linearGradient(
    listOf(Color(0xFF7C3AED), Color(0xFF2563EB))
)

val CyanBlueGradient = Brush.horizontalGradient(
    listOf(Color(0xFF06B6D4), Color(0xFF3B82F6))
)

val SunsetNeonGradient = Brush.horizontalGradient(
    listOf(Color(0xFFEC4899), Color(0xFF8B5CF6))
)

val GreenTealGradient = Brush.horizontalGradient(
    listOf(Color(0xFF10B981), Color(0xFF06B6D4))
)

val CardGlowBorderBrush = Brush.linearGradient(
    listOf(Color(0xFF8B5CF6).copy(alpha = 0.6f), Color(0xFF06B6D4).copy(alpha = 0.6f))
)
