package com.vaayugo.core.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val VaayuDarkColorScheme = darkColorScheme(
    primary            = VaayuCyan,
    onPrimary          = Color(0xFF003730),
    primaryContainer   = VaayuCyanDark,
    onPrimaryContainer = VaayuCyanLight,
    secondary          = VaayuCyanDark,
    onSecondary        = Color.Black,
    secondaryContainer = Color(0xFF004D45),
    onSecondaryContainer = VaayuCyan,
    background         = VaayuBackground,
    onBackground       = TextPrimary,
    surface            = VaayuSurface,
    onSurface          = TextPrimary,
    surfaceVariant     = VaayuSurface2,
    onSurfaceVariant   = TextSecondary,
    outline            = VaayuOutline,
    outlineVariant     = Color(0xFF333333),
    error              = VaayuRed,
    onError            = Color.White,
    errorContainer     = Color(0xFF4A1010),
    onErrorContainer   = Color(0xFFFFDAD6),
    scrim              = Color(0x99000000),
    inverseSurface     = TextPrimary,
    inverseOnSurface   = VaayuBackground,
    inversePrimary     = VaayuCyanDark,
)

@Composable
fun VaayuGoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = VaayuDarkColorScheme,
        typography  = VaayuTypography,
        shapes      = VaayuShapes,
        content     = content
    )
}