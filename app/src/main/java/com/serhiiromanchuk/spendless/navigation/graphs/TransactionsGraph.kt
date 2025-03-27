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
        route = Feature.Transactions.route
    ) {
        composable(
            route = Screen.Dashboard.route
        ) { entry ->
            val transactionsSharedViewModel =
                entry.sharedViewModel<TransactionsSharedViewModel>(navigationState.navController)

            DashboardScreenRoot(
                navigateToSettings = { navigationState.navigateTo(Screen.Settings.route) },
                viewModel = transactionsSharedViewModel,
                navigateToAllTransactions = { navigationState.navigateTo(Screen.AllTransactions.route) },
                navigateToPinPrompt = { navigationState.navigateToPinPrompt() }
            )
        }

        composable(
            route = Screen.AllTransactions.route
        ) { entry ->

            val transactionsSharedViewModel =
                entry.sharedViewModel<TransactionsSharedViewModel>(navigationState.navController)

            AllTransactionsScreenRoot(
                onBackClick = { navigationState.popBackStack() },
                viewModel = transactionsSharedViewModel
            )
        }
    }
}