package com.vaayugo.core.ui.theme


import androidx.compose.ui.graphics.Color

// ── Backgrounds (from video frame analysis) ────────────────────────────────
val VaayuBackground  = Color(0xFF0D0D0D)   // page background
val VaayuSurface     = Color(0xFF1A1A1A)   // cards, sidebar
val VaayuSurface2    = Color(0xFF242424)   // elevated / hover state
val VaayuOutline     = Color(0xFF2A2A2A)   // borders, dividers

// ── Brand Cyan (primary accent — buttons, active nav, links) ──────────────
val VaayuCyan        = Color(0xFF00E5C8)
val VaayuCyanDark    = Color(0xFF00B8A1)   // pressed / container
val VaayuCyanLight   = Color(0xFF64FFED)   // on-container text

// ── Text ──────────────────────────────────────────────────────────────────
val TextPrimary      = Color(0xFFF0F0F0)
val TextSecondary    = Color(0xFF9E9E9E)
val TextHint         = Color(0xFF616161)

// ── Status colors (order badges — matched from video) ─────────────────────
val VaayuGreen       = Color(0xFF00C853)   // DELIVERED / Active / Approved
val VaayuRed         = Color(0xFFE53935)   // CANCELLED / FAILED / Blocked
val VaayuOrange      = Color(0xFFFF6D00)   // PENDING / Processing
val VaayuYellow      = Color(0xFFFFD600)   // Star ratings

// ── Stat card accents (matched from shopkeeper dashboard) ─────────────────
val VaayuPurple      = Color(0xFF7C3AED)   // Total Products card border
val VaayuPink        = Color(0xFFEC4899)   // Shop Rating card border
val StatBorderCyan   = Color(0xFF00E5C8)   // Total Orders card border
val StatBorderYellow = Color(0xFFFFD600)   // Delivery Rating card border