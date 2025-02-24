package com.serhiiromanchuk.auth.presentation.screens.registration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.screens.registration.handling.RegistrationUiEvent
import com.serhiiromanchuk.auth.presentation.screens.registration.handling.RegistrationUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.FilledButton
import com.serhiiromanchuk.core.presentation.designsystem.theme.AppColors
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme

@Composable
internal fun RegistrationForm(
    state: RegistrationUiState,
    onEvent: (RegistrationUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        UsernameTextField(state = state)

        FilledButton(
            text = stringResource(R.string.next),
            onClick = { onEvent(RegistrationUiEvent.NextButtonClicked) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            },
            enabled = state.isNextButtonEnabled
        )
    }
}

@Composable
private fun UsernameTextField(
    state: RegistrationUiState,
    modifier: Modifier = Modifier
) {
    Column {
        val validationState = state.usernameValidationState

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = AppColors.OnBackgroundOpacity08,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            var isFocused by remember { mutableStateOf(false) }

            BasicTextField(
                state = state.username,
                modifier = modifier
                    .wrapContentWidth()
                    .onFocusChanged {
                        isFocused = it.isFocused
                    }
                ,
                textStyle = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                ),
                lineLimits = TextFieldLineLimits.SingleLine,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                decorator = { innerBox ->
                    Box(
                        modifier = Modifier.wrapContentWidth().padding(horizontal = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        innerBox()

                        if (state.username.text.isEmpty() && !isFocused) {
                            UsernamePlaceholder()
                        }
                    }
                }
            )
        }

        if (!validationState.isValidUsername) {
            Text(
                text = when  {
                    !validationState.hasMinLength -> "Username must be at least 3 characters long"
                    !validationState.isWithinMaxLength -> "Username cannot exceed 14 characters."
                    else -> ""
                },
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.error
            )
        }

    }

}

@Composable
private fun UsernamePlaceholder(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = R.string.username_placeholder),
        modifier = modifier,
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFEF7FF
)
@Composable
private fun PreviewRegistrationForm() {
    SpendLessTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            RegistrationForm(
                state = RegistrationUiState(),
                onEvent = {}
            )
        }
    }
}