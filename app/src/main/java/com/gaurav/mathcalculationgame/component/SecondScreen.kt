package com.gaurav.mathcalculationgame.component

import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gaurav.mathcalculationgame.R
import com.gaurav.mathcalculationgame.generateQuestion
import java.util.Locale


@Composable
fun SecondScreen(navController: NavController, category: String) {

    // UI for the second screen
    val context = LocalContext.current
    var life by remember { mutableIntStateOf(3) }
    var score by remember { mutableIntStateOf(0) }
    var remainingTime by remember { mutableStateOf("30") }
    var myQuestion by remember { mutableStateOf("My Question : ") }
    val myAnswer = remember { mutableStateOf("") }
    var isEnable by remember { mutableStateOf(true) }
    var correctAnswer by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = "math", block = {
        val resultList = generateQuestion(category)
        myQuestion = resultList[0].toString()
        correctAnswer = resultList[1] as Int
    })

    val totalTimeInMillis = remember { mutableStateOf(30000L) }
    val timer = remember {
        mutableStateOf(
            object : CountDownTimer(totalTimeInMillis.value,1000){
                override fun onTick(millisUntilFinished: Long) {

                    remainingTime = String.format(Locale.getDefault(),"%02d",millisUntilFinished/1000)

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
                    .padding(it)
                    .paint(
                        painter = painterResource(id = R.drawable.second_page_bg),
                        contentScale = ContentScale.FillBounds
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(text = "Life: ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = life.toString(), fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Score: ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = score.toString(), fontSize = 18.sp)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Remaining Time: ", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = remainingTime, fontSize = 18.sp)
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
                            if (myAnswer.toString().isEmpty()){
                                Toast.makeText(context, "Write an answer or click the Next button",Toast.LENGTH_SHORT).show()
                            }else{
                                timer.value.cancel()
                                isEnable = false
                                if (myAnswer.value.toInt() == correctAnswer) {
                                   score+=10
                                    myQuestion="Hurray!! Your answer is correct"
                                    myAnswer.value=""
                                }else{
                                    life-=1
                                    myQuestion="Oops!! Your answer is wrong"

                                }

                            }
                        })

                    ButtonOKorNext(buttonText = "Next", isEnable = true,
                        myOnClick = {
                            timer.value.cancel()
                            timer.value.start()
                            if (life==0){
                                myQuestion="Game Over!!"
                                Toast.makeText(context, "Game Over!!",Toast.LENGTH_SHORT).show()
                            }else{
                                val newResultList = generateQuestion(category)
                                myQuestion = newResultList[0].toString()
                                correctAnswer = newResultList[1] as Int
                                myAnswer.value=""
                                isEnable = true
                            }
                        })
                }


            }

        }
    )
}