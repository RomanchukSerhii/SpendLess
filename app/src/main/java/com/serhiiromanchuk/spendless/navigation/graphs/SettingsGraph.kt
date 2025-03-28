package com.serhiiromanchuk.spendless.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.serhiiromanchuk.settings.presentation.screens.SettingsSharedViewModel
import com.serhiiromanchuk.settings.presentation.screens.preferences.PreferencesScreenRoot
import com.serhiiromanchuk.settings.presentation.screens.security.SecurityScreenRoot
import com.serhiiromanchuk.settings.presentation.screens.settings.SettingsScreen
import com.serhiiromanchuk.spendless.navigation.NavigationState
import com.serhiiromanchuk.spendless.navigation.routes.Feature
import com.serhiiromanchuk.spendless.navigation.routes.Screen
import com.serhiiromanchuk.spendless.navigation.sharedViewModel

fun NavGraphBuilder.settingsGraph(navigationState: NavigationState) {
    navigation(
        startDestination = Screen.Settings.route,
        route = Feature.Settings.route
    ) {
        composable(
            route = Screen.Settings.route
        ) { entry ->
            val viewModel =
                entry.sharedViewModel<SettingsSharedViewModel>(navigationState.navController)

            SettingsScreen(
                navigateBack = { navigationState.navigateToTransactions() },
                navigateToPreferences = { navigationState.navigateTo(Screen.Preferences.route) },
                navigateToSecurity = { navigationState.navigateTo(Screen.Security.route) },
                navigateToLogin = { navigationState.navigateToLogin() },
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.Preferences.route
        ) { entry ->
            val viewModel =
                entry.sharedViewModel<SettingsSharedViewModel>(navigationState.navController)

            PreferencesScreenRoot(
                navigateBack = { navigationState.popBackStack() },
                navigateToPinPrompt = { navigationState.navigateToPinPrompt() },
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.Security.route
        ) { entry ->
            val viewModel =
                entry.sharedViewModel<SettingsSharedViewModel>(navigationState.navController)

            SecurityScreenRoot(
                navigateBack = { navigationState.popBackStack() },
                navigateToPinPrompt = { navigationState.navigateToPinPrompt() },
                viewModel = viewModel
            )
        }
    }
}