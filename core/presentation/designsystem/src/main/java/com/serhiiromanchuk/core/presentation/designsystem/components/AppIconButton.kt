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
    contentDescription: String,
    onClick: () -> Unit,
    colors: IconButtonColorsScheme,
    isEnabled: Boolean = true,
    @DrawableRes iconResId: Int
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor by animateColorAsState(
        targetValue = colors.containerColor(isPressed),
        label = "buttonBackgroundColor"
    )

    Box(
        modifier = Modifier
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

// Я или чего то не вижу, или эта функция ничего кроме установки стандартной иконки ничего не делает?
// Если так, то ее можно было задать и в IconButtonTemplate. И в целом давай все таки создадим разные
// иконки как в макете, назови FilledIconButton, StandardIconButton и т.д.

/* Эта функция нужна, чтобы не повторялся код, во всех 3 кнопках когда вызываешь много параметров
код получается длинный. А так вызов функции упрщается до 3 строк. это удобно. Из всех вариантов
что я перепробовала он наиболее удобен. Менять не буду. В демо видно как и какие кнопки использовать */

@Composable
fun AppIconButton(
    contentDescription: String,
    onClick: () -> Unit,
    colors: IconButtonColorsScheme,
    isEnabled: Boolean = true,
    @DrawableRes iconResId: Int = R.drawable.settings
) {
    IconButtonTemplate(
        contentDescription = contentDescription,
        onClick = onClick,
        colors = colors,
        isEnabled = isEnabled,
        iconResId = iconResId
    )
}

@Composable
fun IconButtonDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppIconButton(
            contentDescription = "Filled",
            onClick = {  },
            colors = AppIconButtonColors.Filled
        )

        AppIconButton(
            contentDescription = "Standard",
            onClick = {  },
            colors = AppIconButtonColors.Standard
        )

        AppIconButton(
            contentDescription = "Error",
            onClick = {  },
            colors = AppIconButtonColors.Error
        )
    }
}
 //  у меня превью работает без указания API
@Preview(showBackground = true)
@Composable
private fun IconButtonPrev() {
    SpendLessTheme {
        IconButtonDemo()
    }
}