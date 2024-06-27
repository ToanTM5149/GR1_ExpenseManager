package com.toan.expensemanagergr1

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.toan.expensemanagergr1.route.AddRoute
import com.toan.expensemanagergr1.ui.controlbar.BotottomNavBar

@Composable
fun Home() {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = { BotottomNavBar(navController)}
    ) { innerPadding ->
        AddRoute(navController, modifier = Modifier.padding(innerPadding))
    }
}