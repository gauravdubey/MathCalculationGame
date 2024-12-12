package com.gaurav.mathcalculationgame.navigation

sealed class AppRoutes(val route: String) {
    object SplashPage : AppRoutes("splash_screen")
    object FirstPage : AppRoutes("first_screen")
    object SecondPage : AppRoutes("second_screen/{category}"){
        fun createRoute(category: String) = "second_screen/$category"
    }
    object ResultPage : AppRoutes("result_screen/{score}"){
        fun createRoute(score:Int) = "result_screen/$score"
    }
}