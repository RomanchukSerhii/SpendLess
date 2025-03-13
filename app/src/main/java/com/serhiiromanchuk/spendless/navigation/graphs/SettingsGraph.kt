package com.serhiiromanchuk.spendless.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.serhiiromanchuk.settings.presentation.screens.preferences.PreferencesScreenRoot
import com.serhiiromanchuk.settings.presentation.screens.security.SecurityScreenRoot
import com.serhiiromanchuk.settings.presentation.screens.settings.SettingsScreen
import com.serhiiromanchuk.spendless.navigation.NavigationState
import com.serhiiromanchuk.spendless.navigation.routes.Feature
import com.serhiiromanchuk.spendless.navigation.routes.Screen

fun NavGraphBuilder.settingsGraph(navigationState: NavigationState) {
    navigation(
        startDestination = Screen.Settings.route,
        route = Feature.Settings.route
    ) {
        composable(
            route = Screen.Settings.route
        ) {
            SettingsScreen(
                onBackClick = { },
                onPreferencesClick = { navigationState.navigateTo(Screen.Preferences.route) },
                onSecurityClick = { navigationState.navigateTo(Screen.Security.route) },
                onLogoutClick = { }
            )
        }
        composable(
            route = Screen.Preferences.route
        ) {
            PreferencesScreenRoot(
                onBackClick = { navigationState.popBackStack() }
            )
        }
        composable(
            route = Screen.Security.route
        ) {
            SecurityScreenRoot(
                onBackClick = { navigationState.popBackStack() }
            )
        }
    }
}