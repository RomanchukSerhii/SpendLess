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

fun NavGraphBuilder.authGraph(navigationState: NavigationState) {
    navigation(
        startDestination = Screen.Login.route,
        route = Feature.Auth.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreenRoot(
                navigateToTransactions = { username ->
                    navigationState.navigateToTransactions(username)
                },
                navigateToRegistration = { navigationState.navigateTo(Screen.CreateUsername.route) }
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
                navigateToTransactions = { username ->
                    navigationState.navigateToTransactions(username)
                },
                viewModel = registrationSharedViewModel
            )
        }

        composable(
            route = Screen.PINPrompt.route
        ) {
            PinPromptScreenRoot(
                onLogOutClick = { navigationState.navigateTo(Screen.CreateUsername.route) }
            )
        }
    }
}