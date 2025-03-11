package com.serhiiromanchuk.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.serhiiromanchuk.spendless.navigation.routes.Feature

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Feature.Auth.route
    ) {
        authGraph(navController)
        transactionsGraph(navController)
        settingsGraph(navController)
    }
}