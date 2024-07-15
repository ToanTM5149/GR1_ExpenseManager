package com.toan.expensemanagergr1.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.toan.expensemanagergr1.ui.screens.loginandsignup.Login
import com.toan.expensemanagergr1.ui.screens.loginandsignup.Signup
import com.toan.expensemanagergr1.ui.screens.user.AddExpense
import com.toan.expensemanagergr1.ui.screens.user.Profile
import com.toan.expensemanagergr1.ui.screens.user.TotalInfo

@Composable
fun AddRoute(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = "/auth", modifier = modifier) {
        userRoutes(navController)
        authRoutes(navController)
        adminRoutes(navController)
    }
}

fun NavGraphBuilder.authRoutes(navController: NavHostController) {
    navigation(startDestination = Screens.Login.route, route = "/auth") {
        composable(route = Screens.Login.route) {
            Login(navController)
        }
        composable(route = Screens.SignUp.route) {
            Signup(navController)
        }
    }
}

fun NavGraphBuilder.userRoutes(navController: NavHostController) {
    navigation(startDestination = Screens.Home.route, route = "/user") {
        composable(route = Screens.Home.route) {
            TotalInfo(navController)
        }
        composable(route = Screens.AddExpense.route) {
            AddExpense(navController)
        }
        composable(route = Screens.Profile.route) {
            Profile(navController)
        }
    }
}
fun NavGraphBuilder.adminRoutes(navController: NavHostController) {
    navigation(startDestination = Screens.Home.route, route = "/admin") {

    }
}
