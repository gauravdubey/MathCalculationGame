package com.gaurav.mathcalculationgame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gaurav.mathcalculationgame.component.FirstScreen
import com.gaurav.mathcalculationgame.component.ResultScreen
import com.gaurav.mathcalculationgame.component.SecondScreen
import com.gaurav.mathcalculationgame.component.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = AppRoutes.SplashPage.route) {

        composable(AppRoutes.SplashPage.route) {
            SplashScreen(navController)
        }
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
        composable(
            AppRoutes.ResultPage.route,
            arguments = listOf(navArgument("score"){ defaultValue=0 })
        ) { backStackEntry ->
            val finalScore = backStackEntry.arguments?.getInt("score")?:0
            ResultScreen(navController=navController, score = finalScore)

        }
    }
}