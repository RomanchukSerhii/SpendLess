package com.serhiiromanchuk.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.serhiiromanchuk.core.presentation.designsystem.theme.SpendLessTheme
import com.serhiiromanchuk.spendless.navigation.NavigationRoot
import com.serhiiromanchuk.transactions.screens.Dashboard
import com.serhiiromanchuk.transactions.screens.TransactionsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
//                val navController = rememberNavController()
//                NavigationRoot(navController = navController)
                Dashboard()
            }
        }
    }
}