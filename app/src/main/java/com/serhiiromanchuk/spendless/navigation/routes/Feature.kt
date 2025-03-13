package com.serhiiromanchuk.spendless.navigation.routes

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Feature(val route: String) {
    data object Auth : Feature(ROUTE_AUTH)

    data object Transactions : Feature(ROUTE_TRANSACTIONS) {
        const val USERNAME = "username"

        val routeWithArgs = "$route/{$USERNAME}"

        val arguments = listOf(
            navArgument(USERNAME) { type = NavType.StringType }
        )
    }

    data object Settings : Feature(ROUTE_SETTINGS)

    companion object {
        private const val ROUTE_AUTH = "auth_feature"
        private const val ROUTE_TRANSACTIONS = "auth_transactions"
        private const val ROUTE_SETTINGS = "auth_settings"
    }
}