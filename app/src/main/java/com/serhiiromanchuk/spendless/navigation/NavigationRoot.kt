package com.serhiiromanchuk.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.serhiiromanchuk.spendless.navigation.graphs.authGraph
import com.serhiiromanchuk.spendless.navigation.graphs.settingsGraph
import com.serhiiromanchuk.spendless.navigation.graphs.transactionsGraph
import com.serhiiromanchuk.spendless.navigation.routes.Feature

@Composable
fun NavigationRoot(
    navigationState: NavigationState,
) {
    NavHost(
        navController = navigationState.navController,
        startDestination = Feature.Auth.route
    ) {
        authGraph(navigationState)
        transactionsGraph(navigationState)
        settingsGraph(navigationState)
    }
}