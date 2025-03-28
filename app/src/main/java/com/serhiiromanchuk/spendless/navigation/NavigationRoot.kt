package com.serhiiromanchuk.spendless.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import com.serhiiromanchuk.core.domain.repository.SessionRepository
import com.serhiiromanchuk.spendless.navigation.graphs.authGraph
import com.serhiiromanchuk.spendless.navigation.graphs.settingsGraph
import com.serhiiromanchuk.spendless.navigation.graphs.transactionsGraph
import com.serhiiromanchuk.spendless.navigation.routes.Feature
import kotlinx.coroutines.launch

@Composable
fun NavigationRoot(
    navigationState: NavigationState,
    isLaunchedFromWidget: Boolean,
    sessionRepository: SessionRepository
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                coroutineScope.launch {
                    if (sessionRepository.isUserLoggedIn() && sessionRepository.isSessionExpired()) {
                        navigationState.navigateToPinPrompt()
                    }
                }
            }
        }
        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    NavHost(
        navController = navigationState.navController,
        startDestination = Feature.Auth.route
    ) {
        authGraph(navigationState, sessionRepository.isUserLoggedIn())
        transactionsGraph(navigationState, isLaunchedFromWidget)
        settingsGraph(navigationState)
    }
}