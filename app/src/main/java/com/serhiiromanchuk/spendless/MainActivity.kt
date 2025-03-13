package com.serhiiromanchuk.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.spendless.navigation.NavigationRoot
import com.serhiiromanchuk.spendless.navigation.rememberNavigationState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
                val navigationState = rememberNavigationState()
//                val navController = rememberNavController()
                NavigationRoot(navigationState = navigationState)
            }
        }
    }
}