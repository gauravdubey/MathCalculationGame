package com.gaurav.mathcalculationgame.component

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gaurav.mathcalculationgame.R
import com.gaurav.mathcalculationgame.navigation.AppRoutes

@Composable
fun ResultScreen(navController: NavController, score: Int) {
    // UI for result screen

    val myContext = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.third_page_bg),
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(140.dp))
        Text(
            text = "Congratulations !! ",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Your Score:  $score",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0E6111)
        )

        Spacer(modifier = Modifier.height(80.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    navController.popBackStack(AppRoutes.FirstPage.route, inclusive = false)
                },
                modifier = Modifier.size(150.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, colorResource(id = R.color.blue)),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Play Again",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.blue)
                )
            }

            Button(
                onClick = {

                    myContext.finish()
                },
                modifier = Modifier.size(150.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                border = BorderStroke(1.dp, colorResource(id = R.color.blue)),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Exit",
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.blue)
                )
            }
        }
    }
}