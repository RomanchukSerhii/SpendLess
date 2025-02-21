package com.serhiiromanchuk.spendless.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.serhiiromanchuk.auth.presentation.screens.login.LoginScreenRoot
import com.serhiiromanchuk.settings.presentation.screens.preferences.PreferencesScreenRoot
import com.serhiiromanchuk.settings.presentation.screens.security.SecurityScreenRoot
import com.serhiiromanchuk.settings.presentation.screens.settings.SettingsScreen
import com.serhiiromanchuk.spendless.navigation.routes.Feature
import com.serhiiromanchuk.spendless.navigation.routes.Screen
import com.serhiiromanchuk.transactions.screens.dashboard.DashboardScreenRoot

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Login.route,
        route = Feature.Auth.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreenRoot(
                onLogInClick = { navController.navigate(Screen.PINPrompt.route) },
                onRegistrationClick = { navController.navigate(Screen.Registration.route) }
            )
        }

        composable(
            route = Screen.Registration.route
        ) {

        }
        composable(
            route = Screen.CreatePIN.route
        ) {

        }

        composable(
            route = Screen.OnboardingPreferences.route
        ) {

        }

        composable(
            route = Screen.PINPrompt.route
        ) {

        }
    }
}

fun NavGraphBuilder.transactionsGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Dashboard.route,
        route = Feature.Transactions.route
    ) {
        composable(
            route = Screen.Dashboard.route
        ) {
            DashboardScreenRoot(
                onSettingsClick = { navController.navigate(Feature.Settings.route) }
            )
        }
        composable(
            route = Screen.AllTransactions.route
        ) {

        }
    }
}

fun NavGraphBuilder.settingsGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Settings.route,
        route = Feature.Settings.route
    ) {
        composable(
            route = Screen.Settings.route
        ) {
            SettingsScreen(
                onBackClick = { },
                onPreferencesClick = { navController.navigate(Screen.Preferences.route) },
                onSecurityClick = { navController.navigate(Screen.Security.route) },
                onLogoutClick = { }
            )
        }
        composable(
            route = Screen.Preferences.route
        ) {
            PreferencesScreenRoot(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.Security.route
        ) {
            SecurityScreenRoot(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}