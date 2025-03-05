package com.serhiiromanchuk.auth.presentation.screens.pin_prompt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiEvent
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiEvent.*
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.handling.PinPromptUiState

class PinPromptViewModel : ViewModel() {
    var state by mutableStateOf(PinPromptUiState())
        private set

    fun onEvent(event: PinPromptUiEvent) {
        when (event) {
            BackspaceButtonClicked -> TODO()
            LogOutClicked -> TODO()
            is NumberButtonClicked -> TODO()
        }
    }
}