package com.vaayugo.core.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaayugo.core.domain.model.OrderStatus
import com.vaayugo.core.ui.theme.*

// ── VaayuCard ─────────────────────────────────────────────────────────────
@Composable
fun VaayuCard(
    modifier  : Modifier = Modifier,
    onClick   : (() -> Unit)? = null,
    content   : @Composable ColumnScope.() -> Unit
) {
    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = VaayuSurface),
            shape  = RoundedCornerShape(12.dp),
            border = androidx.compose.foundation.BorderStroke(0.5.dp, VaayuOutline),
            content = content
        )
    } else {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = VaayuSurface),
            shape  = RoundedCornerShape(12.dp),
            border = androidx.compose.foundation.BorderStroke(0.5.dp, VaayuOutline),
            content = content
        )
    }
}

// ── StatCard — matches the website's dashboard cards exactly ───────────────
// Has a colored left-border accent (like the website's colored top-border)
@Composable
fun StatCard(
    label       : String,
    value       : String,
    modifier    : Modifier = Modifier,
    accentColor : Color = VaayuCyan,
    valueColor  : Color = TextPrimary,
    subLabel    : String? = null
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(VaayuSurface)
            .border(0.5.dp, VaayuOutline, RoundedCornerShape(10.dp))
    ) {
        // Colored top border accent (matches website)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(accentColor)
        )
        Column(
            modifier = Modifier.padding(start = 14.dp, end = 14.dp, top = 18.dp, bottom = 14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(label, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            Text(value, style = MaterialTheme.typography.headlineSmall,
                color = valueColor, fontWeight = FontWeight.Bold)
            if (subLabel != null) {
                Text(subLabel, style = MaterialTheme.typography.bodySmall, color = TextHint)
            }
        }
    }
}

// ── StatusBadge — matches website's pill badges exactly ───────────────────
@Composable
fun StatusBadge(status: String, modifier: Modifier = Modifier) {
    val (bg, textColor) = when (status.lowercase().trim()) {
        "delivered"           -> VaayuGreen.copy(alpha = 0.15f)  to VaayuGreen
        "active", "approved", "in stock", "in_stock", "open"
            -> VaayuGreen.copy(alpha = 0.15f)  to VaayuGreen
        "cancelled"           -> VaayuRed.copy(alpha = 0.15f)    to VaayuRed
        "failed"              -> VaayuRed.copy(alpha = 0.15f)    to VaayuRed
        "blocked"             -> VaayuRed.copy(alpha = 0.15f)    to VaayuRed
        "out of stock", "out_of_stock"
            -> VaayuRed.copy(alpha = 0.15f)    to VaayuRed
        "pending"             -> VaayuOrange.copy(alpha = 0.15f) to VaayuOrange
        "processing"          -> VaayuOrange.copy(alpha = 0.15f) to VaayuOrange
        "finalized"           -> VaayuCyan.copy(alpha = 0.15f)   to VaayuCyan
        else                  -> VaayuSurface2                   to TextSecondary
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bg)
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text  = status.uppercase().replace("_", " "),
            color = textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )
    }
}

fun OrderStatus.toDisplayString() = when (this) {
    OrderStatus.PENDING    -> "pending"
    OrderStatus.PROCESSING -> "processing"
    OrderStatus.DELIVERED  -> "delivered"
    OrderStatus.CANCELLED  -> "cancelled"
    OrderStatus.FAILED     -> "failed"
}

// ── VaayuButton — cyan filled, matches website Sign In button ─────────────
@Composable
fun VaayuButton(
    text      : String,
    onClick   : () -> Unit,
    modifier  : Modifier = Modifier,
    enabled   : Boolean  = true,
    isLoading : Boolean  = false,
    leadingIcon: ImageVector? = null
) {
    Button(
        onClick  = onClick,
        modifier = modifier.fillMaxWidth().height(52.dp),
        enabled  = enabled && !isLoading,
        colors   = ButtonDefaults.buttonColors(
            containerColor         = VaayuCyan,
            contentColor           = Color(0xFF003730),
            disabledContainerColor = VaayuCyan.copy(alpha = 0.35f),
            disabledContentColor   = Color(0xFF003730).copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier    = Modifier.size(20.dp),
                color       = Color(0xFF003730),
                strokeWidth = 2.dp
            )
        } else {
            if (leadingIcon != null) {
                Icon(leadingIcon, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
            }
            Text(text, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        }
    }
}

// ── VaayuOutlinedButton ───────────────────────────────────────────────────
@Composable
fun VaayuOutlinedButton(
    text    : String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    color   : Color = VaayuCyan
) {
    OutlinedButton(
        onClick  = onClick,
        modifier = modifier.height(46.dp),
        border   = androidx.compose.foundation.BorderStroke(1.dp, color),
        colors   = ButtonDefaults.outlinedButtonColors(contentColor = color),
        shape    = RoundedCornerShape(10.dp)
    ) {
        Text(text, fontWeight = FontWeight.Medium, fontSize = 14.sp)
    }
}

// ── VaayuTextField — dark outlined field, matches website inputs ───────────
@Composable
fun VaayuTextField(
    value         : String,
    onValueChange : (String) -> Unit,
    label         : String,
    modifier      : Modifier = Modifier,
    leadingIcon   : ImageVector? = null,
    isPassword    : Boolean = false,
    isError       : Boolean = false,
    errorText     : String? = null,
    singleLine    : Boolean = true,
    readOnly      : Boolean = false,
    placeholder   : String? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value         = value,
            onValueChange = onValueChange,
            label         = { Text(label) },
            placeholder   = if (placeholder != null) {{ Text(placeholder, color = TextHint) }} else null,
            leadingIcon   = if (leadingIcon != null) {
                { Icon(leadingIcon, null, tint = TextSecondary) }
            } else null,
            trailingIcon  = when {
                isPassword -> {{
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            null, tint = TextSecondary
                        )
                    }
                }}
                trailingContent != null -> trailingContent
                else -> null
            },
            visualTransformation = if (isPassword && !passwordVisible)
                PasswordVisualTransformation() else VisualTransformation.None,
            isError    = isError,
            singleLine = singleLine,
            readOnly   = readOnly,
            modifier   = Modifier.fillMaxWidth(),
            colors     = OutlinedTextFieldDefaults.colors(
                focusedBorderColor     = VaayuCyan,
                unfocusedBorderColor   = VaayuOutline,
                focusedLabelColor      = VaayuCyan,
                unfocusedLabelColor    = TextSecondary,
                cursorColor            = VaayuCyan,
                focusedTextColor       = TextPrimary,
                unfocusedTextColor     = TextPrimary,
                disabledTextColor      = TextSecondary,
                errorBorderColor       = VaayuRed,
                errorLabelColor        = VaayuRed,
                errorCursorColor       = VaayuRed,
                focusedContainerColor  = VaayuSurface,
                unfocusedContainerColor= VaayuSurface,
                disabledContainerColor = VaayuSurface,
                errorContainerColor    = VaayuSurface,
            ),
            shape = RoundedCornerShape(10.dp)
        )
        if (isError && errorText != null) {
            Text(
                text     = errorText,
                color    = VaayuRed,
                style    = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}

// ── LoadingScreen ─────────────────────────────────────────────────────────
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), Alignment.Center) {
        CircularProgressIndicator(color = VaayuCyan, strokeWidth = 2.dp)
    }
}

// ── ErrorScreen ───────────────────────────────────────────────────────────
@Composable
fun ErrorScreen(message: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(message, color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))
        VaayuOutlinedButton("Retry", onRetry, Modifier.width(120.dp))
    }
}

// ── EmptyState ────────────────────────────────────────────────────────────
@Composable
fun EmptyState(message: String, icon: ImageVector? = null, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (icon != null) {
            Icon(icon, null, tint = TextHint, modifier = Modifier.size(56.dp))
            Spacer(Modifier.height(12.dp))
        }
        Text(message, color = TextHint, style = MaterialTheme.typography.bodyMedium)
    }
}

// ── StarRating ────────────────────────────────────────────────────────────
@Composable
fun StarRating(rating: Float, modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Filled.Star, null, tint = VaayuYellow, modifier = Modifier.size(14.dp))
        Spacer(Modifier.width(3.dp))
        Text("%.1f".format(rating), color = VaayuYellow,
            style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
    }
}

// ── SectionHeader ─────────────────────────────────────────────────────────
@Composable
fun SectionHeader(
    title    : String,
    modifier : Modifier = Modifier,
    action   : @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge,
            color = TextPrimary, fontWeight = FontWeight.SemiBold)
        action?.invoke()
    }
}

// ── VaayuDivider ──────────────────────────────────────────────────────────
@Composable
fun VaayuDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(modifier = modifier, color = VaayuOutline, thickness = 0.5.dp)
}

// ── VaayuTopBar ───────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaayuTopBar(
    title          : String,
    navigationIcon : @Composable (() -> Unit)? = null,
    actions        : @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = { Text(title, style = MaterialTheme.typography.headlineSmall, color = TextPrimary) },
        navigationIcon = { navigationIcon?.invoke() },
        actions = actions,
        colors  = TopAppBarDefaults.topAppBarColors(
            containerColor        = VaayuSurface,
            scrolledContainerColor= VaayuSurface,
            titleContentColor     = TextPrimary,
            actionIconContentColor= TextSecondary,
            navigationIconContentColor = TextSecondary
        )
    )
}

// ── CategoryChip ─────────────────────────────────────────────────────────
@Composable
fun CategoryChip(
    label     : String,
    selected  : Boolean,
    onClick   : () -> Unit,
    modifier  : Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick  = onClick,
        label    = { Text(label, fontSize = 12.sp) },
        modifier = modifier,
        colors   = FilterChipDefaults.filterChipColors(
            selectedContainerColor  = VaayuCyan.copy(alpha = 0.15f),
            selectedLabelColor      = VaayuCyan,
            containerColor          = VaayuSurface2,
            labelColor              = TextSecondary
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled            = true,
            selected           = selected,
            selectedBorderColor= VaayuCyan,
            borderColor        = VaayuOutline,
            borderWidth        = 0.5.dp,
            selectedBorderWidth= 1.dp
        ),
        shape = RoundedCornerShape(20.dp)
    )
}

// ── UserAvatar (initials circle) ──────────────────────────────────────────
@Composable
fun UserAvatar(name: String, size: Dp = 36.dp, modifier: Modifier = Modifier) {
    val initials = name.split(" ").take(2).joinToString("") { it.firstOrNull()?.toString() ?: "" }.uppercase()
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(VaayuCyan.copy(alpha = 0.2f)),
        contentAlignment = Alignment.Center
    ) {
        Text(initials, color = VaayuCyan, fontSize = (size.value * 0.35).sp, fontWeight = FontWeight.Bold)
    }
}

// ── VaayuSnackbarHost helper ──────────────────────────────────────────────
@Composable
fun VaayuSnackbarHost(hostState: SnackbarHostState) {
    SnackbarHost(hostState) { data ->
        Snackbar(
            snackbarData    = data,
            containerColor  = VaayuSurface2,
            contentColor    = TextPrimary,
            actionColor     = VaayuCyan,
            shape           = RoundedCornerShape(8.dp)
        )
    }
}