package com.toan.expensemanagergr1

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.toan.expensemanagergr1.route.AddRoute
import com.toan.expensemanagergr1.route.Screens
import com.toan.expensemanagergr1.ui.controlbar.BotottomNavBar

@Composable
fun Home() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in Screens.screenWithBottomNav

    Scaffold (
        bottomBar = { if (showBottomBar) BotottomNavBar(navController)}
    ) { innerPadding ->
        AddRoute(navController, modifier = Modifier.padding(innerPadding))
    }
}