package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.core.presentation.designsystem.theme.ButtonColors
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme

// Переименовал названия кнопок, давай такие общие элементы именовать как в макете, чтобы потом
// не путаться. По возможности переименуй все остальные элементы.
@Composable
fun FilledButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors.filled()
    ) {
        Text(text, style = MaterialTheme.typography.titleMedium)
    }
}

// Если названия элемента совпадают со стандартными компонентами, как в данном случае, то давай
// договоримся перед началом ставить названия нашего приложения
@Composable
fun SpendLessTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    TextButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonColors.textBtn(isPressed)
    ) {
        Text(text, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ButtonExamples() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FilledButton(text = "Filled Button", onClick = { /* TODO */ })
        FilledButton(text = "Disabled Button", onClick = {}, enabled = false)

        SpendLessTextButton(text = "Text Button", onClick = { /* TODO */ })
        SpendLessTextButton(text = "Disabled Text Button", onClick = {}, enabled = false)
    }
}

// Обрати внимание я под АПИ нашего приложения до 35го, но превью почему-то пока на нем не работает,
// поэтому указывай АПИ ниже в самом превью как тут. И чтобы видеть как корректно отображаются цвета
// не забывай оборачивать компонент в тему SpendLessTheme.
@Preview(apiLevel = 34)
@Composable
fun PreviewButtons() {
    SpendLessTheme {
        ButtonExamples()
    }
}