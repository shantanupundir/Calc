package com.example.mycalc

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private var firstNumber = 0.0
    private var operation = ""
    private var isNewOperation = true

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn0.setOnClickListener { appendNumber("0") }
        binding.btn1.setOnClickListener { appendNumber("1") }
        binding.btn2.setOnClickListener { appendNumber("2") }
        binding.btn3.setOnClickListener { appendNumber("3") }
        binding.btn4.setOnClickListener { appendNumber("4") }
        binding.btn5.setOnClickListener { appendNumber("5") }
        binding.btn6.setOnClickListener { appendNumber("6")}
        binding.btn7.setOnClickListener { appendNumber("7") }
        binding.btn8.setOnClickListener { appendNumber("8") }
        binding.btn9.setOnClickListener { appendNumber("9") }
        binding.btnDot.setOnClickListener { appendNumber(".") }



        binding.btnPercentage.setOnClickListener { setOperation("%") }
        binding.btnAddition.setOnClickListener { setOperation("+") }
        binding.btnSubtraction.setOnClickListener { setOperation("-") }
        binding.btnMultiply.setOnClickListener { setOperation("x") }
        binding.btnDivide.setOnClickListener { setOperation("÷") }


        binding.btnEquals.setOnClickListener { calculateResult() }
        binding.btnClear.setOnClickListener { clearCalculator() }
        binding.btnBackspace.setOnClickListener { deleteNum() }


    }
    private fun calculateResult(){
        try {
            val secondNumber: Double = binding.resultTextView.text.toString().toDouble()
            val result: Double = when (operation){
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "x" -> firstNumber * secondNumber
                "÷" -> firstNumber / secondNumber
                else -> secondNumber
            }
            binding.previousCalculationTextView.text="$firstNumber $operation $secondNumber ="
            binding.resultTextView.text=result.toString()
            isNewOperation=true

        }catch (e: Exception){
            binding.resultTextView.text="Error"
        }
    }
    private fun appendNumber(number:String){
        if(isNewOperation){
            binding.resultTextView.text=number
            isNewOperation = false
        }else{
            binding.resultTextView.append(number)
        }
    }
    private fun setOperation(operator:String){
        firstNumber = binding.resultTextView.text.toString().toDouble()
        operation = operator
        isNewOperation = true
        binding.previousCalculationTextView.text="$firstNumber $operation"
    }
    private fun clearCalculator(){
        firstNumber = 0.0
        binding.previousCalculationTextView.text=""
        binding.resultTextView.text ="0.0"
        operation=""
        isNewOperation=true
    }
    private fun deleteNum() {
        if (binding.resultTextView.text.isNotEmpty() && binding.resultTextView.text != "0.0" && binding.resultTextView.text != "Error"){
            binding.resultTextView.text=binding.resultTextView.text.dropLast(1)
        }else{
            Toast.makeText(this,"Invalid Operation",Toast.LENGTH_SHORT).show()
        }
    }
}

