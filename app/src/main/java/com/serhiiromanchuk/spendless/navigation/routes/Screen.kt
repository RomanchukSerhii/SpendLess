package com.serhiiromanchuk.spendless.navigation.routes

sealed class Screen(val route: String) {

    data object Login : Screen(ROUTE_LOGIN)
    data object CreateUsername : Screen(ROUTE_CREATE_USERNAME)
    data object CreatePIN : Screen(ROUTE_CREATE_PIN)
    data object OnboardingPreferences : Screen(ROUTE_ONBOARDING_PREFERENCES)
    data object PinPrompt : Screen(ROUTE_PIN_PROMPT)

    data object Dashboard : Screen(ROUTE_DASHBOARD)
    data object AllTransactions : Screen(ROUTE_ALL_TRANSACTIONS)

    data object Settings : Screen(ROUTE_SETTINGS)
    data object Preferences : Screen(ROUTE_PREFERENCES)
    data object Security : Screen(ROUTE_SECURITY)

    companion object {
        private const val ROUTE_LOGIN = "login_screen"
        private const val ROUTE_CREATE_USERNAME = "registration_screen"
        private const val ROUTE_CREATE_PIN = "create_pin_screen"
        private const val ROUTE_ONBOARDING_PREFERENCES = "onboarding_preferences_screen"
        private const val ROUTE_PIN_PROMPT = "pin_prompt_screen"

        private const val ROUTE_DASHBOARD = "dashboard_screen"
        private const val ROUTE_ALL_TRANSACTIONS = "all_transactions_screen"

        private const val ROUTE_SETTINGS = "settings_screen"
        private const val ROUTE_PREFERENCES = "preferences_screen"
        private const val ROUTE_SECURITY = "security_screen"
    }
}