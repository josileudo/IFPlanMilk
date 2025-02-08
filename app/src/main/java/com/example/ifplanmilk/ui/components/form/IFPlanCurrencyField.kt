package com.example.ifplanmilk.ui.components.form

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModel
import com.example.ifplanmilk.data.utils.CurrencyMaxTransformation
import com.example.ifplanmilk.data.utils.doubleToString
import kotlin.math.pow

class CurrencyInputViewModel : ViewModel() {
    private val _amount = mutableStateOf(0.0)

    fun updateAmount(newAmount: Double) {
        _amount.value = newAmount
    }
}

@Composable
fun IFPlanCurrencyField(
    viewModel: CurrencyInputViewModel = CurrencyInputViewModel(),
    onValueChange: (Double) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    value: Double = 0.0,
    decimalsNumber: Int = 2,
    lastItem: Boolean = false
) {

    var textFieldValue by remember { mutableStateOf(
        doubleToString(value, decimalsNumber)
    ) }

    IfPlanFormItem(
        modifier = modifier,
        value = textFieldValue,
        onValueChange = { newValue ->
            val cleanValue = newValue.filter { it.isDigit() }
            textFieldValue = cleanValue
            val factor = 10.0.pow(decimalsNumber.toDouble())
            val doubleValue = cleanValue.toDoubleOrNull()?.div(factor) ?: 0.0
            viewModel.updateAmount(doubleValue)
            onValueChange(doubleValue)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = if(lastItem) ImeAction.Done else ImeAction.Next
        ),
        label = label,
        visualTransformation = CurrencyMaxTransformation(decimalsNumber = decimalsNumber),
    )
}

