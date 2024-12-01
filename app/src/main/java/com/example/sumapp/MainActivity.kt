package com.example.sumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@Composable
fun Home(){

    var numberOne by remember { mutableStateOf("") }
    var numberTwo by remember { mutableStateOf("") }
    var numberOneError by remember { mutableStateOf(false) }
    var numberTwoError by remember { mutableStateOf(false) }
    var sum by remember { mutableDoubleStateOf(0.00)}

    fun sumNumbers(firstNumber: String, secondNumber: String): Double {
        return (firstNumber.toDouble() + secondNumber.toDouble())
    }

    Column(
        Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        TextField(value = numberOne, onValueChange = { numberOne = it; numberOneError = false }, isError = numberOneError)

        if(numberOneError) {
            Text(text = "Preencha", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        TextField(value = numberTwo, onValueChange = { numberTwo = it; numberTwoError = false }, isError = numberTwoError)

        if(numberTwoError) {
            Text(text = "Preencha", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
        }

        Button(
            onClick = {
                if(numberOne.isEmpty()) {
                    numberOneError = true
                }
                else if (numberTwo.isEmpty()) {
                    numberTwoError = true
                }
                else {
                    sum = sumNumbers(numberOne, numberTwo)
                }
            }
        )
        {
            Text("Somar")
        }
        Text("Resultado: $sum")
    }
}