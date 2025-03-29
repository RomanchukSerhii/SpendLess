package com.serhiiromanchuk.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.serhiiromanchuk.core.domain.repository.SessionRepository
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.spendless.navigation.NavigationRoot
import com.serhiiromanchuk.spendless.navigation.rememberNavigationState
import com.serhiiromanchuk.spendless.widget.SpendLessWidget
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val sessionRepository: SessionRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLaunchedFromWidget = intent.getBooleanExtra(SpendLessWidget.KEY_WIDGET_INTENT, false)
        sessionRepository.setLaunchedFromWidget(isLaunchedFromWidget)

        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
                val navigationState = rememberNavigationState()
                NavigationRoot(
                    navigationState = navigationState,
                    sessionRepository = sessionRepository
                )
            }
        }
    }
}