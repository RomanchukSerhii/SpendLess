package com.serhiiromanchuk.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.serhiiromanchuk.spendless.navigation.routes.Feature
import com.serhiiromanchuk.spendless.navigation.routes.Screen

class NavigationState(
    val navController: NavHostController
) {
    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun popBackStack() = navController.popBackStack()

    fun navigateToTransactions() {
        navController.navigate(Feature.Transactions.route) {
            popUpTo(0) { inclusive = true }
        }
    }

    fun navigateToSettings() {
        navController.navigate(Feature.Settings.route)
    }

    fun navigateToPinPrompt() {
        navController.navigate(Screen.PinPrompt.route) {
            popUpTo(0) { inclusive = true }
        }
    }

    fun navigateToLogin() {
        navController.navigate(Screen.Login.route) {
            popUpTo(Screen.Login.route) { inclusive = true }
        }
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}