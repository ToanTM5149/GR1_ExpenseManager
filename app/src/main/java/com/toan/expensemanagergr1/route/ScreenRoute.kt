package com.toan.expensemanagergr1.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class Screen (val route: String, val icon: ImageVector)

object Screens {
    val Home = Screen("/home", Icons.Default.Home)
    val AddExpense = Screen("/add", Icons.Default.AddCircle)
    val Profile = Screen("/profile", Icons.Default.AccountCircle)
    val Login = Screen("/login", Icons.Default.Person)
    val SignUp = Screen("/signup", Icons.Default.Person)
}