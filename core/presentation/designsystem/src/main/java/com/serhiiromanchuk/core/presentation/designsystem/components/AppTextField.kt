package com.serhiiromanchuk.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    isDisabled: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    AppCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyLarge,
            enabled = !isDisabled,
            isError = isError,
            shape = RoundedCornerShape(16.dp),
            colors = textFieldColors(),
            modifier = modifier.fillMaxWidth(),
            label = label,
            supportingText = supportingText,
            placeholder = placeholder,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation
        )
    }
}

@Composable
private fun textFieldColors(): TextFieldColors {
    val colorScheme = MaterialTheme.colorScheme

    return OutlinedTextFieldDefaults.colors(
        focusedTextColor = colorScheme.onSurface,
        unfocusedTextColor = colorScheme.onSurface,
        disabledTextColor = colorScheme.onSurface.copy(alpha = 0.38f),
        errorTextColor = colorScheme.error,
        cursorColor = colorScheme.primary,
        errorCursorColor = colorScheme.error,
        focusedBorderColor = colorScheme.primary,
        unfocusedBorderColor = Color.Transparent,
        disabledBorderColor = colorScheme.onSurface.copy(alpha = 0.38f),
        errorBorderColor = colorScheme.error,
        focusedPlaceholderColor = colorScheme.onSurfaceVariant.copy(alpha = 0.65f),
        unfocusedPlaceholderColor = colorScheme.onSurfaceVariant,
        errorPlaceholderColor = colorScheme.error,
        focusedSupportingTextColor = colorScheme.onSurfaceVariant,
        unfocusedSupportingTextColor = colorScheme.onSurfaceVariant,
        disabledSupportingTextColor = colorScheme.onSurfaceVariant.copy(alpha = 0.38f),
        errorSupportingTextColor = colorScheme.error
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFEF7FF
)
@Composable
private fun AppTextFieldPreview() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppTextField(
            value = "Input",
            onValueChange = {},
        )
    }
//        AppTextField(
//            value = "Input",
//            onValueChange = {},
//            label = "Label",
//            supportingText = "Supporting text",
//        )
//        AppTextField(
//            value = "Input",
//            onValueChange = {},
//            label = "Label",
//            supportingText = "Supporting text",
//            isError = true
//        )
//        AppTextField(
//            value = "Input",
//            onValueChange = {},
//            label = "Label",
//            supportingText = "Supporting text",
//            isDisabled = true
//        )
//    }
}