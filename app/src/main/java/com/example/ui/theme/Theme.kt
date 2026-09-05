package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val ZynovaDarkColorScheme = darkColorScheme(
    primary = NeonPurple,
    onPrimary = TextWhite,
    primaryContainer = Color(0xFF2E1065),
    onPrimaryContainer = NeonPurpleLight,
    secondary = NeonCyan,
    onSecondary = DarkBackground,
    secondaryContainer = Color(0xFF083344),
    onSecondaryContainer = NeonCyanLight,
    tertiary = NeonBlue,
    onTertiary = TextWhite,
    tertiaryContainer = Color(0xFF172554),
    onTertiaryContainer = Color(0xFF93C5FD),
    background = DarkBackground,
    onBackground = TextWhite,
    surface = DarkSurface,
    onSurface = TextWhite,
    surfaceVariant = DarkSurfaceCard,
    onSurfaceVariant = TextMuted,
    outline = DarkBorder,
    outlineVariant = DarkBorderGlow,
    error = NeonCoral,
    onError = TextWhite
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Enforce our branded neon dark style
    content: @Composable () -> Unit
) {
    val colorScheme = ZynovaDarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DarkBackground.toArgb()
            window.navigationBarColor = DarkBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
