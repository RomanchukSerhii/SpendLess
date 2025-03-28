package com.serhiiromanchuk.spendless.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.serhiiromanchuk.auth.presentation.screens.login.LoginScreenRoot
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.PinPromptScreenRoot
import com.serhiiromanchuk.auth.presentation.screens.registration.RegistrationSharedViewModel
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.CreatePinScreenRoot
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.CreateUsernameScreenRoot
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.OnboardingPrefScreenRoot
import com.serhiiromanchuk.spendless.navigation.NavigationState
import com.serhiiromanchuk.spendless.navigation.routes.Feature
import com.serhiiromanchuk.spendless.navigation.routes.Screen
import com.serhiiromanchuk.spendless.navigation.sharedViewModel

fun NavGraphBuilder.authGraph(
    navigationState: NavigationState,
    isUserLoggedIng: Boolean
) {
    navigation(
        startDestination = if (isUserLoggedIng) Screen.PinPrompt.route else Screen.Login.route,
        route = Feature.Auth.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreenRoot(
                navigateToTransactions = { navigationState.navigateToTransactions() },
                navigateToRegistration = { navigationState.navigateTo(Screen.CreateUsername.route) },
                navigateToPinPrompt = { navigationState.navigateToPinPrompt() }
            )
        }

        composable(
            route = Screen.CreateUsername.route
        ) { entry ->
            val registrationSharedViewModel =
                entry.sharedViewModel<RegistrationSharedViewModel>(navigationState.navController)

            CreateUsernameScreenRoot(
                navigateToLogIn = { navigationState.navigateToLogin() },
                navigateNext = { navigationState.navigateTo(Screen.CreatePIN.route) },
                viewModel = registrationSharedViewModel
            )
        }
        composable(
            route = Screen.CreatePIN.route
        ) { entry ->
            val registrationSharedViewModel =
                entry.sharedViewModel<RegistrationSharedViewModel>(navigationState.navController)

            CreatePinScreenRoot(
                navigateBack = { navigationState.popBackStack() },
                navigateNext = { navigationState.navigateTo(Screen.OnboardingPreferences.route) },
                viewModel = registrationSharedViewModel
            )
        }

        composable(
            route = Screen.OnboardingPreferences.route
        ) { entry ->
            val registrationSharedViewModel =
                entry.sharedViewModel<RegistrationSharedViewModel>(navigationState.navController)

            OnboardingPrefScreenRoot(
                navigateBack = { navigationState.popBackStack() },
                navigateToTransactions = { navigationState.navigateToTransactions() },
                viewModel = registrationSharedViewModel
            )
        }

        composable(
            route = Screen.PinPrompt.route
        ) {
            PinPromptScreenRoot(
                navigateBack = {
                    if (navigationState.canNavigateBack()) {
                        navigationState.popBackStack()
                    } else {
                        navigationState.navigateToTransactions()
                    }
                },
                navigateToLogin = { navigationState.navigateTo(Screen.Login.route) }
            )
        }
    }
}