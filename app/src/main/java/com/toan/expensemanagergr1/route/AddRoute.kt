package com.toan.expensemanagergr1.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.toan.expensemanagergr1.ui.screens.Login
import com.toan.expensemanagergr1.ui.screens.Signup
import com.toan.expensemanagergr1.ui.screens.user.AddExpense
import com.toan.expensemanagergr1.ui.screens.user.Profile
import com.toan.expensemanagergr1.ui.screens.user.TotalInfo

@Composable
fun AddRoute(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = Screens.Home.route, modifier = modifier) {
        composable(route = Screens.Home.route) {
            TotalInfo(navController)
        }
        composable(route = Screens.AddExpense.route) {
            AddExpense(navController)
        }
        composable(route = Screens.Profile.route) {
            Profile(navController)
        }
        composable(route = Screens.Login.route) {
            Login(navController)
        }
        composable(route = Screens.SignUp.route) {
            Signup(navController)
        }
    }
}
