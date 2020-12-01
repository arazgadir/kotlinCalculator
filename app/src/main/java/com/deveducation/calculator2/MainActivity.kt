package com.deveducation.calculator2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {


    var digit_on_screen = StringBuilder()
    var operation: Char  = ' '
    var LeftSide:Double = 0.0
    var RightSide:Double = 0.0
    var b :Double = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            Window.text = "0"
            StartCalculator()
            stopOperationButtons()


    }




private fun StartCalculator()
{
    helperButtons()
    operationButtons()
    NumberBUttons()
}



 private fun NumberBUttons()   {

    button1.setOnClickListener { DigitToScreen("1") }
    button2.setOnClickListener { DigitToScreen("2") }
    button3.setOnClickListener { DigitToScreen("3") }
    button4.setOnClickListener { DigitToScreen("4") }
    button5.setOnClickListener { DigitToScreen("5") }
    button6.setOnClickListener { DigitToScreen("6") }
    button7.setOnClickListener { DigitToScreen("7") }
    button8.setOnClickListener { DigitToScreen("8") }
    button9.setOnClickListener { DigitToScreen("9") }
    buttonNull.setOnClickListener { DigitToScreen("0") }
    buttonDot.setOnClickListener { DigitToScreen(".")
        stopOperationButtons()
        stopDotButton() }
    buttonM.setOnClickListener {
        DigitToScreen("-")
        stopOperationButtons()
    }
}



    private fun DigitToScreen(digit:String)         /*  ДОБАВЛЕНИЕ  НА  ЭКРАН */
    {
           runClearButton()
           runOperationButtons()
           buttonM.isClickable = false

           digit_on_screen.append(digit)
           Window.text = digit_on_screen.toString()
    }




    private fun operationButtons() {                                   /* КНОПКИ  + - * /    */
    buttonSubtract.setOnClickListener {
        selectOperation('-' )
        stopOperationButtons()
    }

    buttonAddition.setOnClickListener {
        selectOperation('+')
        stopOperationButtons()
    }
    buttonDivide.setOnClickListener {
        selectOperation('/')
        stopOperationButtons()
    }
    buttonMultiply.setOnClickListener {
        selectOperation('*')
        stopOperationButtons()
    }

}



private fun selectOperation(c:Char)                     /* ПРИСВАИВАНИЕ  +  -  /  *   */
{
    operation = c
    LeftSide = digit_on_screen.toString().toDouble()
    runDotButton()
    digit_on_screen.clear()

    Window.text = operation.toString()

}





private fun helperButtons()                 /* КНОПКА ОЧИСТКА, УДАЛЕНИЕ , РАВНО    */
{
    buttonC.setOnClickListener {
        digit_on_screen.clear()
        Window.text = 0.toString()
        stopOperationButtons()
        runDotButton()
        stopClearButton()
        buttonM.isClickable = true
    }


    buttonClear.setOnClickListener {

        if (Window.text.isNotEmpty() ) {
            clearDigit()
        }
           else if (Window.text.isEmpty()){
                stopOperationButtons()
            }
    }



    buttonResult.setOnClickListener {
        equalOperation()
        buttonResult.isClickable = false

    }

}



    private fun equalOperation() {                   /* ФУЕКЦИЯ РАВЕНСТВА */

        RightSide = digit_on_screen.toString().toDouble()

        Window.text = digit_on_screen.toString()

        digit_on_screen.clear()


when (operation) {

    '+' -> {
        var sum = MathOperation.add(LeftSide, RightSide)
        var A = sum.toString().split(".")
        if (A[1] == "0"){
            Window.text = A[0]
        }
        else {
            Window.text = sum.toString()
        }
        digit_on_screen.append(sum)
    }



    '-' -> {
        var subtract = MathOperation.subtract(LeftSide, RightSide)
        var S = subtract.toString().split(".")
        if (S[1] == "0"){
            Window.text = S[0]
        }
        else {
            Window.text = subtract.toString()
        }
        digit_on_screen.append(subtract)
    }



    '*' -> {
        var multiply = MathOperation.multiply(LeftSide, RightSide)
        var M = multiply.toString().split(".")
        if (M[1] == "0"){
            Window.text = M[0]
        }
        else {
            Window.text = multiply.toString()
        }

        digit_on_screen.append(multiply)
    }



    '/' -> {

        var divide = MathOperation.divide(LeftSide, RightSide)

        if (RightSide != 0.0) {
            var D = divide.toString().split(".")
            if (D[1] == "0") {
                Window.text = D[0]
            } else {
                Window.text = divide.toString()
            }
        }
            else  {
                Window.text = "Error. Press C"
            }

        digit_on_screen.append(divide)
    }

}

 }


    private fun clearDigit() {

    val length = digit_on_screen.length

    digit_on_screen.deleteCharAt(length - 1)
    Window.text = digit_on_screen.toString()

}

    private fun runOperationButtons() {
        buttonSubtract.isClickable = true
        buttonAddition.isClickable = true
        buttonMultiply.isClickable = true
        buttonDivide.isClickable = true
        buttonResult.isClickable = true

    }
    private fun stopOperationButtons() {
        buttonSubtract.isClickable = false
        buttonAddition.isClickable = false
        buttonMultiply.isClickable = false
        buttonDivide.isClickable = false
        buttonResult.isClickable = false
    }

    private fun runDotButton() {
        buttonDot.isClickable = true
    }
    private fun stopDotButton() {
        buttonDot.isClickable = false
    }

    private fun stopClearButton() {
        buttonClear.isClickable = false
    }
    private fun runClearButton() {
        buttonClear.isClickable = true
    }


}

class MathOperation {

    companion object {

        fun add(left_side: Double, right_side: Double): Double {

            val sum = left_side + right_side
            return sum
        }

        fun subtract(left_side: Double, right_side: Double): Double {
            val subtract = left_side - right_side
            return subtract
        }

        fun divide(left_side: Double, right_side: Double): Double {

            val divide = left_side / right_side
                return divide

        }

        fun multiply(left_side: Double, right_side: Double): Double {
            val product = left_side * right_side
            return product
        }
        fun percent(left_side: Double, right_side: Double): Double {
            val percent = left_side +  ((left_side/100)*right_side)
            return percent
        }


    }

}



































