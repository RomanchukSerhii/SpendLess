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
        route = Feature.Settings.routeWithArgs,
        arguments = Feature.Settings.arguments
    ) {

        composable(
            route = Screen.Settings.route
        ) { entry ->
            val username = entry.arguments?.getString(Feature.Settings.USERNAME)
                ?: throw IllegalArgumentException("Username must be provided but was missing.")

            entry.sharedViewModel<SettingsSharedViewModel>(
                navigationState.navController,
                username
            )

            SettingsScreen(
                onBackClick = { },
                onPreferencesClick = { navigationState.navigateTo(Screen.Preferences.route) },
                onSecurityClick = { navigationState.navigateTo(Screen.Security.route) },
                onLogoutClick = { }
            )
        }

        composable(
            route = Screen.Preferences.route
        ) { entry ->
            val viewModel = entry.sharedViewModel<SettingsSharedViewModel>(
                navigationState.navController
            )

            PreferencesScreenRoot(
                onBackClick = { navigationState.popBackStack() },
                viewModel = viewModel
            )
        }

        composable(
            route = Screen.Security.route
        ) { entry ->
            val viewModel = entry.sharedViewModel<SettingsSharedViewModel>(
                navigationState.navController
            )

            SecurityScreenRoot(
                onBackClick = { navigationState.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}