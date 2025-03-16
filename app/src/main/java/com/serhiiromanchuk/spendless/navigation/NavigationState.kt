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

    fun navigateToTransactions(
        username: String
    ) {
        navController.navigate("${Feature.Transactions.route}/$username") {
            popUpTo(Feature.Transactions.route) { inclusive = true }
        }
    }

    fun navigateToSettings(
        username: String
    ) {
        navController.navigate("${Feature.Settings.route}/$username")
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