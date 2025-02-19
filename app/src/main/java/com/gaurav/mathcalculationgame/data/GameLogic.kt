package com.gaurav.mathcalculationgame.data

import kotlin.random.Random

/**
 * Generates a question based on the selected category.
 */
fun generateQuestion(selectedCategory: String) : ArrayList<Any>{
    val number1 = Random.nextInt(1, 100)
    val number2 = Random.nextInt(1, 100)
    val textQuestion: String
    val correctAnswer: Int
    when (selectedCategory) {
        "Add" -> {
            textQuestion = "$number1 + $number2"
            correctAnswer = number1 + number2
        }

        "Sub" -> {
            if (number1 >= number2) {
                textQuestion = "$number1 - $number2"
                correctAnswer = number1 - number2
            } else {
                textQuestion = "$number2 - $number1"
                correctAnswer = number2 - number1
            }
        }

        "Mul" -> {
            val mulNumber1 = Random.nextInt(1, 16)
            val mulNumber2 = Random.nextInt(1, 16)
            textQuestion = "$mulNumber1 * $mulNumber2"
            correctAnswer = mulNumber1 * mulNumber2
        }

        else -> {
            if (number1 == 0 || number2 == 0) {

                textQuestion = "0 / 1"
                correctAnswer = 0    // 15 % 7 = 1 --> 15 - 1 = 14 --> 14 / 7 = 2

            } else if (number1 >= number2) {

                val newBigNumber = number1 - (number1 % number2)
                textQuestion = "$newBigNumber / $number2"
                correctAnswer = newBigNumber / number2

            } else {

                val newBigNumber = number2 - (number2 % number1)
                textQuestion = "$newBigNumber / $number1"
                correctAnswer = newBigNumber / number1

            }
        }

    }
    val gameResultList = ArrayList<Any>()
    gameResultList.add(textQuestion)
    gameResultList.add(correctAnswer)

    return gameResultList
}