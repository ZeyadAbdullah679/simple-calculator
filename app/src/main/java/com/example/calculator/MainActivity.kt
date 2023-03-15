package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var firstNumber = 0.0
    private var currentOperation: Operation? = null

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCallBacks()
    }


    private fun addCallBacks() {
        binding.apply {
            buttonClear.setOnClickListener {
                clearInput()
                firstNumber = 0.0
            }
            buttonPlus.setOnClickListener {
                if (textviewResult.text.isNotBlank())
                    prepareOperation(Operation.PLUS)
            }
            buttonMinus.setOnClickListener {
                if (textviewResult.text.isNotBlank())
                    prepareOperation(Operation.MINUS)
            }
            buttonMulti.setOnClickListener {
                if (textviewResult.text.isNotBlank())
                    prepareOperation(Operation.MULTIPLY)
            }
            buttonDiv.setOnClickListener {
                if (textviewResult.text.isNotBlank())
                    prepareOperation(Operation.DIV)
            }
            buttonReminder.setOnClickListener {
                if (textviewResult.text.isNotBlank())
                    prepareOperation(Operation.REMINDER)
            }
            buttonSign.setOnClickListener {
                if (textviewResult.text.isNotBlank()) {
                    val current = textviewResult.text.toString().toDouble() * -1.0
                    textviewResult.text = current.toString()
                }
            }
            buttonEqual.setOnClickListener {
                if (textviewResult.text.isNotBlank() && currentOperation != null)
                    textviewResult.text = preformOperation().toString()
            }
        }
    }

    private fun preformOperation(): Double {
        val secondNumber = binding.textviewResult.text.toString().toDouble()
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
        firstNumber = binding.textviewResult.text.toString().toDouble()
        clearInput()
        currentOperation = op
    }

    private fun clearInput() {
        binding.textviewResult.text = ""
        currentOperation = null
    }


    fun onClickListener(btn: View) {
        val buttonValue = (btn as Button).text.toString()
        val currentText = binding.textviewResult.text.toString()
        if (currentText in listOf("Infinity", "-Infinity")) {
            binding.textviewResult.text = buttonValue
        }
        else if(!(currentText.contains(".") && buttonValue==".")){
            val newTextNumber = currentText + buttonValue
            binding.textviewResult.text = newTextNumber
        }
    }
}