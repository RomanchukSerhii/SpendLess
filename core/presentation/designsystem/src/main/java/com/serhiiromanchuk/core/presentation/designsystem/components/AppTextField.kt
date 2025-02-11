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
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    supportingText: String,
    isError: Boolean = false,
    isDisabled: Boolean = false
) {
    val colorScheme = MaterialTheme.colorScheme
    val textColor = if (isDisabled) colorScheme.onSurface.copy(alpha = 0.38f) else colorScheme.onSurface

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.bodyMedium.copy(color = textColor),
        enabled = !isDisabled,
        isError = isError,
        shape = RoundedCornerShape(16.dp),
        colors = textFieldColors(),
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                color = if (isDisabled) colorScheme.onSurface.copy(alpha = 0.38f) else colorScheme.onSurface
            )
        },
        supportingText = {
            Text(
                text = supportingText,
                color = when {
                    isError -> colorScheme.error
                    isDisabled -> colorScheme.onSurfaceVariant.copy(alpha = 0.38f)
                    else -> colorScheme.onSurfaceVariant
                }
            )
        }
    )
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
fun AppTextFieldPreview() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppTextField(
            value = "Input",
            onValueChange = {},
            label = "Label",
            supportingText = "Supporting text",
        )
        AppTextField(
            value = "Input",
            onValueChange = {},
            label = "Label",
            supportingText = "Supporting text",
            isError = true
        )
        AppTextField(
            value = "Input",
            onValueChange = {},
            label = "Label",
            supportingText = "Supporting text",
            isDisabled = true
        )
    }
}