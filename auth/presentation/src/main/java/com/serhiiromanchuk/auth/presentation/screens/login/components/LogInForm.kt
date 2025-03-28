package com.serhiiromanchuk.auth.presentation.screens.login.components

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.serhiiromanchuk.auth.domain.UserValidationState
import com.serhiiromanchuk.auth.presentation.R
import com.serhiiromanchuk.auth.presentation.components.PinLengthError
import com.serhiiromanchuk.auth.presentation.components.UsernameLengthError
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiEvent
import com.serhiiromanchuk.auth.presentation.screens.login.handling.LoginUiState
import com.serhiiromanchuk.core.presentation.designsystem.components.AppFilledButton
import com.serhiiromanchuk.core.presentation.designsystem.components.AppTextField

@Composable
fun LogInForm(
    state: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                // Shadow
                .drawBehind {
                    drawIntoCanvas { canvas ->
                        val shadowColor = Color(0xFF180040).copy(alpha = 0.08f)
                        val paint = Paint().apply {
                            isAntiAlias = true
                            val blurRadius = 20.dp.toPx()
                            asFrameworkPaint().apply {
                                color = shadowColor.toArgb()
                                maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
                            }
                        }

                        val spread = 4.dp.toPx()

                        canvas.drawRoundRect(
                            left = -spread,
                            top = 6.dp.toPx() - spread,
                            right = size.width + spread,
                            bottom = size.height + 6.dp.toPx() + spread,
                            radiusX = 16.dp.toPx(),
                            radiusY = 16.dp.toPx(),
                            paint = paint
                        )
                    }
                },
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UsernameTextField(
                username = state.username,
                onValueChange = { username ->
                    val filteredText = username.filter { it.isLetterOrDigit() }
                    onEvent(LoginUiEvent.UsernameTextChanged(filteredText))
                },
                userValidationState = state.userValidationState,
                focusRequester = focusRequester
            )

            PinTextField(
                pin = state.pin,
                onEvent = onEvent,
                userValidationState = state.userValidationState
            )
        }


        // Log in button
        AppFilledButton(
            text = stringResource(R.string.log_in),
            onClick = { onEvent(LoginUiEvent.LogInButtonClicked) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
    }
}

@Composable
private fun UsernameTextField(
    username: String,
    onValueChange: (String) -> Unit,
    userValidationState: UserValidationState,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
    ) {
        AppTextField(
            value = username,
            onValueChange = onValueChange,
            modifier = Modifier.focusRequester(focusRequester),
            placeholder = {
                Text(
                    text = stringResource(R.string.username),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        UsernameLengthError(userValidationState)
    }
}

@Composable
fun PinTextField(
    pin: String,
    onEvent: (LoginUiEvent) -> Unit,
    userValidationState: UserValidationState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AppTextField(
            value = pin,
            onValueChange = { onEvent(LoginUiEvent.PinTextChanged(it)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.log_in),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    onEvent(LoginUiEvent.LogInButtonClicked)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.NumberPassword
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        PinLengthError(userValidationState)
    }
}