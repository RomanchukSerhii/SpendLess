package com.serhiiromanchuk.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.serhiiromanchuk.auth.presentation.screens.registration.create_pin.CreatePinScreenRoot
import com.serhiiromanchuk.auth.presentation.screens.login.LoginScreenRoot
import com.serhiiromanchuk.auth.presentation.screens.registration.onboarding_pref.OnboardingPrefScreenRoot
import com.serhiiromanchuk.auth.presentation.screens.pin_prompt.PinPromptScreenRoot
import com.serhiiromanchuk.auth.presentation.screens.registration.create_username.CreateUsernameScreenRoot
import com.serhiiromanchuk.auth.presentation.screens.registration.RegistrationSharedViewModel
import com.serhiiromanchuk.settings.presentation.screens.preferences.PreferencesScreenRoot
import com.serhiiromanchuk.settings.presentation.screens.security.SecurityScreenRoot
import com.serhiiromanchuk.settings.presentation.screens.settings.SettingsScreen
import com.serhiiromanchuk.spendless.navigation.routes.Feature
import com.serhiiromanchuk.spendless.navigation.routes.Screen
import com.serhiiromanchuk.transactions.screens.dashboard.DashboardScreenRoot
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.CreateUsername.route,
        route = Feature.Auth.route
    ) {
        composable(
            route = Screen.Login.route
        ) {
            LoginScreenRoot(
                navigateToLogIn = { navController.navigate(Screen.PINPrompt.route) },
                navigateToRegistration = { navController.navigate(Screen.CreateUsername.route) }
            )
        }

        composable(
            route = Screen.CreateUsername.route
        ) { entry ->
            val registrationSharedViewModel = entry.sharedViewModel<RegistrationSharedViewModel>(navController)
            CreateUsernameScreenRoot(
                navigateToLogIn = { navController.navigate(Screen.Login.route) },
                navigateNext = { navController.navigate(Screen.CreatePIN.route) },
                viewModel = registrationSharedViewModel
            )
        }
        composable(
            route = Screen.CreatePIN.route
        ) { entry ->
            val registrationSharedViewModel = entry.sharedViewModel<RegistrationSharedViewModel>(navController)
            CreatePinScreenRoot(
                navigateBack = { navController.popBackStack() },
                navigateNext = { navController.navigate(Screen.OnboardingPreferences.route) },
                viewModel = registrationSharedViewModel
            )
        }

        composable(
            route = Screen.OnboardingPreferences.route
        ) { entry ->
            val registrationSharedViewModel = entry.sharedViewModel<RegistrationSharedViewModel>(navController)
            OnboardingPrefScreenRoot(
                navigateBack = { navController.popBackStack() },
                viewModel = registrationSharedViewModel
            )
        }

        composable(
            route = Screen.PINPrompt.route
        ) {
            PinPromptScreenRoot(
                onLogOutClick = { navController.navigate(Screen.CreateUsername.route)}
            )
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

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}