package com.gaurav.mathcalculationgame.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gaurav.mathcalculationgame.R
import com.gaurav.mathcalculationgame.presentation.component.TopBarView
import com.gaurav.mathcalculationgame.presentation.navigation.AppRoutes

@Composable
fun FirstScreen(navController:NavController) {
    var actionText by remember {
        mutableStateOf("Action will be shown here")
    }
    // UI for the first screen
    Scaffold(
        topBar = {
            TopBarView(false,navController,"")
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .paint(
                        painter = painterResource(id = R.drawable.first_page_bg),
                        contentScale = ContentScale.FillBounds
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = "Math Game",
                    fontSize = 28.sp,
                    modifier = Modifier
                        .graphicsLayer(alpha = 0.99f) // Enables advanced drawing
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF6200EA), Color(0xFF03DAC5))
                            )
                        )
                        .padding(start = 40.dp, end = 40.dp, top = 20.dp, bottom = 20.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White // Text color set to transparent to enable gradient
                )

                Spacer(modifier = Modifier.height(100.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(
                        onClick = { navController.navigate(AppRoutes.SecondPage.createRoute("Add")) },
                        modifier = Modifier.size(180.dp, 120.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8239E9)),
                        border = BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Addition",
                            fontSize = 24.sp,
                        )
                    }
                    Spacer(modifier = Modifier.width(1.dp))
                    Button(
                        onClick = { navController.navigate(AppRoutes.SecondPage.createRoute("Sub")) },
                        modifier = Modifier.size(180.dp, 120.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8239E9)),
                        border = BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Subtraction",
                            fontSize = 24.sp,
                        )
                    }
                }
                Spacer(modifier = Modifier.size(2.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { navController.navigate(AppRoutes.SecondPage.createRoute("Mul")) },
                        modifier = Modifier.size(180.dp, 120.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8239E9)),
                        border = BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Product",
                            fontSize = 24.sp,
                        )
                    }
                    Spacer(modifier = Modifier.width(2.dp))
                    Button(
                        onClick = { navController.navigate(AppRoutes.SecondPage.createRoute("Div")) },
                        modifier = Modifier.size(180.dp, 120.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8239E9)),
                        border = BorderStroke(2.dp, Color.White),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Division",
                            fontSize = 24.sp,
                        )
                    }
                }
            }

        }
    )
}

