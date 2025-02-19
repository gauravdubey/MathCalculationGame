package com.gaurav.mathcalculationgame.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView(isNavigationIcon: Boolean, navController: NavController, title: String) {

    TopAppBar(
        navigationIcon = {
            if (isNavigationIcon) {
                IconButton(onClick =
                {
                    navController.popBackStack()
                }
                ) {
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
            }
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = when (title) {
                    "Add" -> "Addition"
                    "Sub" -> "Subtraction"
                    "Mul" -> "Multiplication"
                    "Div" -> "Division"
                    else -> "Welcome"
                },
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EA), // Toolbar background color
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White// Title text color
        )

    )
}