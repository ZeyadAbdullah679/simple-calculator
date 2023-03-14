package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var buttonPlus: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonMultiplication: Button
    private lateinit var buttonDiv: Button
    private lateinit var buttonReminder: Button
    private lateinit var buttonEqual: Button
    private lateinit var buttonSign: Button
    private lateinit var textNumber: TextView
    private lateinit var clearButton: Button
    private lateinit var buttonPoint: Button

    private var firstNumber = 0.0
    private var currentOperation: Operation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        addCallBacks()
    }


    private fun addCallBacks() {
        clearButton.setOnClickListener {
            clearInput()
            firstNumber = 0.0
        }
        buttonPlus.setOnClickListener {
            if (textNumber.text.isNotBlank())
                prepareOperation(Operation.PLUS)
        }
        buttonMinus.setOnClickListener {
            if (textNumber.text.isNotBlank())
                prepareOperation(Operation.MINUS)
        }
        buttonMultiplication.setOnClickListener {
            if (textNumber.text.isNotBlank())
                prepareOperation(Operation.MULTIPLY)
        }
        buttonDiv.setOnClickListener {
            if (textNumber.text.isNotBlank())
                prepareOperation(Operation.DIV)
        }
        buttonReminder.setOnClickListener {
            if (textNumber.text.isNotBlank())
                prepareOperation(Operation.REMINDER)
        }
        buttonSign.setOnClickListener {
            if (textNumber.text.isNotBlank()) {
                val current = textNumber.text.toString().toDouble() * -1.0
                textNumber.text = current.toString()
            }
        }
        buttonEqual.setOnClickListener {
            if (textNumber.text.isNotBlank() && currentOperation != null)
                textNumber.text = preformOperation().toString()
        }
    }

    private fun preformOperation(): Double {
        val secondNumber = textNumber.text.toString().toDouble()
        val result = when (currentOperation) {
            Operation.PLUS -> firstNumber + secondNumber
            Operation.MINUS -> firstNumber - secondNumber
            Operation.DIV -> firstNumber / secondNumber
            Operation.MULTIPLY -> firstNumber * secondNumber
            Operation.REMINDER -> firstNumber % secondNumber
            Operation.SIGN -> secondNumber * -1
            null -> secondNumber
        }
        currentOperation = null
        return result
    }

    private fun prepareOperation(op: Operation) {
        firstNumber = textNumber.text.toString().toDouble()
        clearInput()
        currentOperation = op
    }

    private fun clearInput() {
        textNumber.text = ""
        currentOperation = null
    }

    private fun initView() {
        buttonPlus = findViewById(R.id.button_plus)
        buttonMinus = findViewById(R.id.button_minus)
        buttonMultiplication = findViewById(R.id.button_multi)
        buttonDiv = findViewById(R.id.button_div)
        buttonReminder = findViewById(R.id.button_reminder)
        buttonSign = findViewById(R.id.button_sign)
        buttonEqual = findViewById(R.id.button_equal)
        textNumber = findViewById(R.id.textview_result)
        clearButton = findViewById(R.id.button_clear)
        buttonPoint = findViewById(R.id.button_point)
    }

    fun onClickListener(btn: View) {
        val buttonValue = (btn as Button).text.toString()
        val currentText = textNumber.text.toString()
        if (currentText in listOf("Infinity", "-Infinity")) {
            textNumber.text = buttonValue
        }
        else if(!(currentText.contains(".") && buttonValue==".")){
            val newTextNumber = currentText + buttonValue
            textNumber.text = newTextNumber
        }
    }
}