package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.AppIconButtonColors
import com.serhiiromanchuk.core.presentation.designsystem.IconButtonColorsScheme
import com.serhiiromanchuk.core.presentation.designsystem.R
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme

@Composable
fun IconButtonTemplate(
    modifier: Modifier = Modifier,
    contentDescription: String,
    onClick: () -> Unit,
    colors: IconButtonColorsScheme,
    isEnabled: Boolean = true,
    @DrawableRes iconResId: Int =  R.drawable.settings
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor by animateColorAsState(
        targetValue = colors.containerColor(isPressed),
        label = "buttonBackgroundColor"
    )

    Box(
        modifier = modifier
            .size(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                enabled = isEnabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = if (isEnabled) colors.contentColor() else colors.disabledContentColor()
        )
    }
}

@Composable
fun FilledIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButtonTemplate(
        modifier = modifier,
        contentDescription = "Filled",
        onClick = onClick,
        colors = AppIconButtonColors.Filled
    )
}
@Composable
fun StandardIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButtonTemplate(
        modifier = modifier,
        contentDescription = "Standard",
        onClick = onClick,
        colors = AppIconButtonColors.Standard
    )
}

@Composable
fun ErrorIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButtonTemplate(
        modifier = modifier,
        contentDescription = "Error",
        onClick = onClick,
        colors = AppIconButtonColors.Error
    )
}

@Composable
fun OnPrimaryIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @DrawableRes iconResId: Int = R.drawable.settings
) {
    IconButtonTemplate(
        modifier = modifier,
        contentDescription = "On primary",
        onClick = onClick,
        colors = AppIconButtonColors.OnPrimary,
        iconResId = iconResId
    )
}
@Preview(showBackground = true)
@Composable
fun IconButtonDemo() {
    SpendLessTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledIconButton(
                onClick = { }
            )
            StandardIconButton(
                onClick = { }
            )
            ErrorIconButton(
                onClick = { }
            )
        }
    }
}
