package com.gaurav.mathcalculationgame.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gaurav.mathcalculationgame.R

@Composable
fun TextForQuestion(question: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp)
            .height(80.dp)
            .background(color = colorResource(id = R.color.blue)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = question,
            fontSize = 26.sp,
            color = Color.White,
            textAlign = TextAlign.Center // Ensure proper horizontal alignment
        )
    }
}


@Composable
fun TextFieldForAnswer(answer: MutableState<String>) {
    TextField(
        value = answer.value,
        onValueChange = { answer.value = it },
        placeholder = { Text(text = "Enter your answer...") },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.blue), // Background color when focused
            unfocusedContainerColor = colorResource(id = R.color.blue), // Background color when unfocused
            focusedLabelColor = Color.White, // Label color when focused
            unfocusedLabelColor = Color.White, // Label color when unfocused
            focusedIndicatorColor = Color.Transparent, // Indicator color when focused
            unfocusedIndicatorColor = Color.Transparent, // Indicator color when unfocused
            cursorColor = Color.White, // Cursor color
            focusedTextColor = Color.White, // Text color when focused
            unfocusedTextColor = Color.White // Text color when unfocused
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 30.dp, end = 30.dp),
        singleLine = true,
        textStyle = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center),
        shape = RoundedCornerShape(0),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun ButtonOKorNext(buttonText: String, isEnable: Boolean, myOnClick: () -> Unit) {
    Button(
        onClick = myOnClick,
        enabled = isEnable,
        modifier = Modifier.size(150.dp, 46.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        border = BorderStroke(1.dp, colorResource(id = R.color.blue)),
        shape = RoundedCornerShape(5.dp)
    ) {

        Text(
            text = buttonText,
            fontSize = 16.sp,
            color = colorResource(id = R.color.blue),
            fontWeight = FontWeight.Bold
        )
    }

}
