package com.serhiiromanchuk.core.presentation.designsystem.components

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

data class SystemIconsUiController(
    val isStatusBarIconsDark: Boolean = true,
    val isNavigationBarIconsDark: Boolean = true
)

val LocalSystemIconsUiController = compositionLocalOf { SystemIconsUiController() }

@Composable
fun BaseContentLayout(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 16.dp,
    onBackPressed: (() -> Unit)? = null,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = WindowInsets.safeDrawing,
    errorMessage: String? = null,
    background: (@Composable () -> Unit)? = null,
    content: @Composable (BoxScope.() -> Unit)
) {
    val view = LocalView.current
    val systemIconsUiController = LocalSystemIconsUiController.current

    SideEffect {
        val window = (view.context as Activity).window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.isAppearanceLightStatusBars = systemIconsUiController.isStatusBarIconsDark
        insetsController.isAppearanceLightNavigationBars = systemIconsUiController.isNavigationBarIconsDark
    }

    BackHandler(
        enabled = onBackPressed != null,
        onBack = { onBackPressed?.invoke() }
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = containerColor
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (background != null) background()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { topBar() },
                bottomBar = {
                    Box {
                        bottomBar()
                        errorMessage?.let { ErrorMessage(it) }
                    }
                },
                snackbarHost = snackbarHost,
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = floatingActionButtonPosition,
                containerColor = if (background != null) Color.Transparent else containerColor,
                contentColor = contentColor,
                contentWindowInsets = contentWindowInsets,
            ) { paddingValues ->
                Box(
                    modifier = modifier
                        .padding(paddingValues)
                        .padding(horizontal = horizontalPadding)
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun ErrorMessage(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.error)
            .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
            .padding(vertical = 12.dp, horizontal = 16.dp),
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onError,
        textAlign = TextAlign.Center
    )
}