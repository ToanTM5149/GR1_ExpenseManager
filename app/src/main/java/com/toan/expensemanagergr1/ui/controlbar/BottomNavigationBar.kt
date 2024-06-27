package com.toan.expensemanagergr1.ui.controlbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.toan.expensemanagergr1.route.Screens
import com.toan.expensemanagergr1.ui.theme.Zinc

@Composable
fun BotottomNavBar (navController: NavController) {
    val items = listOf(
        Screens.Home,
        Screens.AddExpense,
        Screens.Profile
    )

    BottomAppBar(containerColor = Color.White) {
        items.forEach { screen ->
            IconButton(
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = null,
                    tint = Zinc,
                    modifier = Modifier.size(if (screen == Screens.AddExpense) 45.dp else 35.dp))
            }
        }
    }
}