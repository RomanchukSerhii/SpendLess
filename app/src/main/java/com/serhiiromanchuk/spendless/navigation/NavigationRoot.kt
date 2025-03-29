package com.serhiiromanchuk.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import com.serhiiromanchuk.core.domain.repository.SessionRepository
import com.serhiiromanchuk.spendless.navigation.graphs.authGraph
import com.serhiiromanchuk.spendless.navigation.graphs.settingsGraph
import com.serhiiromanchuk.spendless.navigation.graphs.transactionsGraph
import com.serhiiromanchuk.spendless.navigation.routes.Feature

@Composable
fun NavigationRoot(
    navigationState: NavigationState,
    sessionRepository: SessionRepository
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val isUserSessionExpired = sessionRepository.isUserLoggedIn() && sessionRepository.isSessionExpired()

    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START && isUserSessionExpired) {
                navigationState.navigateToPinPrompt()
            }
        }
        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    NavHost(
        navController = navigationState.navController,
        startDestination = if (!sessionRepository.isUserLoggedIn() || isUserSessionExpired) {
            Feature.Auth.route
        } else {
            Feature.Transactions.route
        }
    ) {
        authGraph(navigationState, isUserSessionExpired)
        transactionsGraph(navigationState)
        settingsGraph(navigationState)
    }
}