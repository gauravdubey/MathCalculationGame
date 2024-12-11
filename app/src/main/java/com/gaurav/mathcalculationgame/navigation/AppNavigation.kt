package com.gaurav.mathcalculationgame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gaurav.mathcalculationgame.component.FirstScreen
import com.gaurav.mathcalculationgame.component.ResultScreen
import com.gaurav.mathcalculationgame.component.SecondScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = AppRoutes.FirstPage.route) {
        composable(AppRoutes.FirstPage.route) {
            FirstScreen(navController)
        }
        composable(
            route=AppRoutes.SecondPage.route,
            arguments = listOf(navArgument("category") { defaultValue = "" }))
        { backStackEntry ->
            val selectedCategory = backStackEntry.arguments?.getString("category") ?: ""
            SecondScreen(navController=navController, category = selectedCategory)
        }
        composable(AppRoutes.ResultPage.route) {
            ResultScreen()

        }
    }
}