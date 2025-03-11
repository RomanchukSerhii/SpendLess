package com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.handling

data class CreatePinUiState(
    val screenMode: ScreenMode = ScreenMode.CreatePin,
    val createdPin: String = "",
    val repeatedPin: String = "",
    val showError: Boolean = false
) {

    sealed interface ScreenMode {
        data object CreatePin : ScreenMode
        data object RepeatPin : ScreenMode
    }
}