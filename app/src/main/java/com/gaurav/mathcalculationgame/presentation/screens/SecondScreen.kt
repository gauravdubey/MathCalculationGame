package com.gaurav.mathcalculationgame.presentation.screens

import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gaurav.mathcalculationgame.R
import com.gaurav.mathcalculationgame.data.generateQuestion
import com.gaurav.mathcalculationgame.presentation.component.ButtonOKorNext
import com.gaurav.mathcalculationgame.presentation.component.TextFieldForAnswer
import com.gaurav.mathcalculationgame.presentation.component.TextForQuestion
import com.gaurav.mathcalculationgame.presentation.component.TopBarView
import com.gaurav.mathcalculationgame.presentation.navigation.AppRoutes
import java.util.Locale


@Composable
fun SecondScreen(navController: NavController, category: String) {

    // UI for the second screen
    val context = LocalContext.current
    var life by rememberSaveable { mutableIntStateOf(3) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var remainingTime by rememberSaveable { mutableStateOf("30") }
    var myQuestion by rememberSaveable { mutableStateOf("My Question : ") }
    val myAnswer = rememberSaveable { mutableStateOf("") }
    var isEnable by rememberSaveable { mutableStateOf(true) }
    var correctAnswer by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(key1 = "math", block = {
        val resultList = generateQuestion(category)
        myQuestion = resultList[0].toString()
        correctAnswer = resultList[1] as Int
    })

    val totalTimeInMillis = remember { mutableStateOf(30000L) }
    val timer = remember {
        mutableStateOf(
            object : CountDownTimer(totalTimeInMillis.value, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                    remainingTime =
                        String.format(Locale.getDefault(), "%02d", millisUntilFinished / 1000)

                }

                override fun onFinish() {
                    cancel()
                    myQuestion = "Sorry, Time is up!"
                    life -= 1
                    isEnable = false

                }


            }.start()
        )
    }

    Scaffold(
        topBar = {
            TopBarView(true, navController, category)
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(it)
                    .paint(
                        painter = painterResource(id = R.drawable.second_page_bg),
                        contentScale = ContentScale.FillBounds
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = remainingTime,
                    style = TextStyle(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .background(Color(0xFF1981B1), CircleShape)
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        modifier = Modifier
                            .width(200.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Life: ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = life.toString(), fontSize = 20.sp)
                    }
                    Row(
                        modifier = Modifier
                            .width(200.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Text(text = "Score: ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = score.toString(), fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                TextForQuestion(myQuestion)
                Spacer(modifier = Modifier.height(30.dp))
                TextFieldForAnswer(answer = myAnswer)
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    ButtonOKorNext(buttonText = "Submit", isEnable = isEnable,
                        myOnClick = {
                            if (myAnswer.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Write an answer or click the Next button",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                timer.value.cancel()
                                isEnable = false
                                if (myAnswer.value.toInt() == correctAnswer) {
                                    score += 10
                                    myQuestion = "Hurray!! Your answer is correct"
                                    myAnswer.value = ""
                                } else {
                                    life -= 1
                                    myQuestion = "Oops!! Your answer is wrong"

                                }

                            }
                        })

                    ButtonOKorNext(buttonText = "Next", isEnable = true,
                        myOnClick = {
                            timer.value.cancel()
                            timer.value.start()
                            if (life == 0) {

                                myQuestion = "Game Over!!"
                                Toast.makeText(context, "Game Over!!", Toast.LENGTH_SHORT).show()
                                navController.navigate(AppRoutes.ResultPage.createRoute(score = score)) {
                                    popUpTo(AppRoutes.FirstPage.route) { inclusive = false }
                                }

                            } else {
                                val newResultList = generateQuestion(category)
                                myQuestion = newResultList[0].toString()
                                correctAnswer = newResultList[1] as Int
                                myAnswer.value = ""
                                isEnable = true
                            }
                        })
                }


            }

        }
    )
}