package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    supportingText: String,
    isError: Boolean = false,
    isDisabled: Boolean = false
) {
    val colorScheme = MaterialTheme.colorScheme
    val textColor = if (isDisabled) colorScheme.onSurface.copy(alpha = 0.38f) else colorScheme.onSurface
    val supportingTextColor = if (isError) colorScheme.error else colorScheme.onSurfaceVariant.copy(alpha = 0.6f)

    Column (
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ){
        Text(
            text = label,
            color = textColor,
            style = MaterialTheme.typography.labelSmall
        )
        // На будущее у OutlinedTextField есть в параметрах label и supporting text, чтобы не лепить
        // с Column. Если ты его использовала для отступов, то их можно было бы отригулировать паддингом.
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            // У тебя здесь было просто TextStyle(color = textColor), так ты просто задаешь цвет, но не
            // задаешь стиль.
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = textColor),
            enabled = !isDisabled,
            isError = isError,
            shape = RoundedCornerShape(16.dp),
            colors = textFieldColors(),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = supportingText,
            color = supportingTextColor,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
 // цвета надо настроить
@Composable
fun textFieldColors(): TextFieldColors {
    val colorScheme = MaterialTheme.colorScheme

    return OutlinedTextFieldDefaults.colors(
        focusedTextColor = colorScheme.onSurface,
        unfocusedTextColor = colorScheme.onSurface,
        disabledTextColor = colorScheme.onSurface.copy(alpha = 0.38f),
        errorTextColor = colorScheme.error,
        cursorColor = colorScheme.primary,
        errorCursorColor = colorScheme.error,
        focusedBorderColor = colorScheme.primary,
        unfocusedBorderColor = colorScheme.outline,
        disabledBorderColor = colorScheme.onSurface.copy(alpha = 0.38f),
        errorBorderColor = colorScheme.error,
        focusedLabelColor = colorScheme.primary,
        unfocusedLabelColor = colorScheme.onSurface,
        disabledLabelColor = colorScheme.onSurface.copy(alpha = 0.38f),
        errorLabelColor = colorScheme.error,
        focusedSupportingTextColor = colorScheme.onSurfaceVariant,
        unfocusedSupportingTextColor = colorScheme.onSurfaceVariant,
        disabledSupportingTextColor = colorScheme.onSurfaceVariant.copy(alpha = 0.38f),
        errorSupportingTextColor = colorScheme.error
    )
}

@Preview(
    apiLevel = 34,
    showBackground = true
)
@Composable
fun CustomTextFieldPreview() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            value = "Input",
            onValueChange = {},
            label = "Label",
            supportingText = "Supporting text",
        )
        CustomTextField(
            value = "Input",
            onValueChange = {},
            label = "Label",
            supportingText = "Supporting text",
            isError = true
        )
        CustomTextField(
            value = "Input",
            onValueChange = {},
            label = "Label",
            supportingText = "Supporting text",
            isDisabled = true
        )
    }
}