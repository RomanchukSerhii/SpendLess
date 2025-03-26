package com.serhiiromanchuk.spendless.navigation.routes

sealed class Feature(val route: String) {

    data object Auth : Feature(ROUTE_AUTH)

    data object Transactions : Feature(ROUTE_TRANSACTIONS)

    data object Settings : Feature(ROUTE_SETTINGS)

    companion object {
        private const val ROUTE_AUTH = "feature_auth"
        private const val ROUTE_TRANSACTIONS = "feature_transactions"
        private const val ROUTE_SETTINGS = "feature_settings"
    }
}