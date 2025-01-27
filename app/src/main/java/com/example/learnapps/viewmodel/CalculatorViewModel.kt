package com.example.learnapps.viewmodel

import androidx.lifecycle.ViewModel
import com.example.learnapps.model.CalculatorData

class CalculatorViewModel : ViewModel()  {

    fun calculateSum(num1: Int, num2: Int) : CalculatorData {
        val sum = num1 + num2
        return CalculatorData(num1, num2, sum)
    }
}