package com.serhiiromanchuk.spendless.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.serhiiromanchuk.spendless.navigation.NavigationState
import com.serhiiromanchuk.spendless.navigation.routes.Feature
import com.serhiiromanchuk.spendless.navigation.routes.Screen
import com.serhiiromanchuk.spendless.navigation.sharedViewModel
import com.serhiiromanchuk.transactions.screens.TransactionsSharedViewModel
import com.serhiiromanchuk.transactions.screens.all_transactions.AllTransactionsScreenRoot
import com.serhiiromanchuk.transactions.screens.dashboard.DashboardScreenRoot

fun NavGraphBuilder.transactionsGraph(navigationState: NavigationState) {
    navigation(
        startDestination = Screen.Dashboard.route,
        route = Feature.Transactions.routeWithArgs,
        arguments = Feature.Transactions.arguments
    ) {
        composable(
            route = Screen.Dashboard.route
        ) { entry ->
            val username = entry.arguments?.getString(Feature.Transactions.USERNAME)
                ?: throw IllegalArgumentException("Username must be provided but was missing.")

            val transactionsSharedViewModel =
                entry.sharedViewModel<TransactionsSharedViewModel>(
                    navigationState.navController,
                    username
                )

            DashboardScreenRoot(
                onSettingsClick = { navigationState.navigateToSettings(username) },
                viewModel = transactionsSharedViewModel,
                onShowAllClick = { navigationState.navigateTo(Screen.AllTransactions.route)}
            )
        }

        composable(
            route = Screen.AllTransactions.route
        ) { entry ->

            val transactionsSharedViewModel =
                entry.sharedViewModel<TransactionsSharedViewModel>(
                    navigationState.navController
                )

            AllTransactionsScreenRoot(
                onBackClick = { navigationState.popBackStack() },
                viewModel = transactionsSharedViewModel
            )
        }
    }
}