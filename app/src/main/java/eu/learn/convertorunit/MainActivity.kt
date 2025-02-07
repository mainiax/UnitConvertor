package eu.learn.convertorunit

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.learn.convertorunit.ui.theme.ConvertorUnitTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConvertorUnitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //ConvertorUnit(Modifier.padding(innerPadding))
                    ConversionResult(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ConversionResult(modifier: Modifier = Modifier){

    var inputValue by remember { mutableStateOf("") }
    val conversionValues = listOf("Centimeter", "Meter", "Kilometer","Feet","Inch","Millimeter" )
    var outPutvalue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var isExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(1.00) }
    var outputConversionFactor = remember { mutableStateOf(1.00) }
    var showResult by remember { mutableStateOf(false) }
    ConvertorUnit(
        modifier,
        outPutvalue = outPutvalue,
        onOutputValue = { it -> outPutvalue = it },
        onOutputUnit ={it -> outputUnit = it},
        inputValue = inputValue,
        onNameChanged = { it -> inputValue = it },
        outPutUnit = outputUnit,
        conversionValues = conversionValues,
        expanded = isExpanded,
        onExpanded = { it -> isExpanded = it },
        outputExpanded = outputExpanded,
        onOutputExpanded = { it -> outputExpanded = it },
        inputUnit =  inputUnit,
        onInputUnit = { inputUnit = it },
        conversionFactor = conversionFactor.value,
        onConversionFactor = { it -> conversionFactor.value = it },
        outPutConversionFactor = outputConversionFactor.value,
        onOutputConversionFactor = { it -> outputConversionFactor.value = it },
        showResult = showResult,
        onShowResult = { it -> showResult = it }
    )

}
@Composable
fun ConvertorUnit(modifier: Modifier = Modifier,
                  inputValue:String, onNameChanged: (String) -> Unit,outPutUnit: String,
                  onOutputUnit: (String) -> Unit,
                  outPutvalue: String, onOutputValue: (String) -> Unit,
                  conversionValues: List<String>,
                  expanded: Boolean ,
                  onExpanded: (Boolean) -> Unit,
                  outputExpanded: Boolean,
                  onOutputExpanded: (Boolean) -> Unit,
                  inputUnit: String, onInputUnit: (String) -> Unit,
                  conversionFactor: Double, onConversionFactor: (Double) -> Unit
                  ,outPutConversionFactor: Double, onOutputConversionFactor: (Double) -> Unit,
                  showResult: Boolean, onShowResult: (Boolean) -> Unit
) {

    val localContext = LocalContext.current
    fun convertUnits(){
        val convertedToNumber = inputValue.toDoubleOrNull() ?: 0.0
        val result =( convertedToNumber * conversionFactor * 100.0/ outPutConversionFactor).roundToInt() / 100.0
        onOutputValue(result.toString())


    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(32.dp)
    ) {
        Text(
            text = "Convertor Unit from $inputUnit",
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(5.dp))

        OutlinedTextField(value = inputValue,
            onValueChange = {
            val convertedToNumber = it.toString()
            onNameChanged(convertedToNumber)

        },
            label = { Text(text = "Enter Value") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row (  horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth() ) {
            Box{
                Button(onClick = { /*TODO*/
                onNameChanged(inputValue.toDoubleOrNull().toString().removeSuffix("null"))
                    onExpanded(true)

                }, contentPadding = PaddingValues(16.dp,0.dp), modifier = Modifier.wrapContentSize()  ) {
                    Text(text ="From" )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            onExpanded(false) /*TODO*/ }
                    ){
                        conversionValues.forEach { label ->
                            DropdownMenuItem(
                                text = { Text(label) },
                                onClick = {
                                    /* Do something... */
                                   onExpanded(false)
                                   onInputUnit(label)
                                    onConversionFactor(
                                        when(label) {
                                            "Centimeter" -> 0.01
                                            "Meter" -> 1.0
                                            "Kilometer" -> 1000.0
                                            "Feet" -> 0.3048
                                            "Inch" -> 0.0254
                                            "Millimeter" -> 0.001
                                            else -> 0.0
                                        }

                                    )
                                 convertUnits()
                                    onShowResult(false)

                                }
                            )
                        }

                    }

                }
            }
            Spacer(modifier = Modifier.padding(horizontal = 16.dp))
            Box{

                val toast = Toast.makeText(localContext, "input value must be greater than 0", Toast.LENGTH_SHORT)
                Button(onClick = { /*TODO*/
                    onNameChanged(inputValue.toDoubleOrNull().toString().removeSuffix("null"))
                    onOutputExpanded(true)

                }, contentPadding = PaddingValues(24.dp,0.dp), modifier = Modifier.wrapContentSize()) {
                    Text(text = "To" )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    DropdownMenu(
                        expanded = outputExpanded,
                        onDismissRequest = { /*TODO*/
                            onOutputExpanded(false)

                        }
                    ){
                        conversionValues.map { label ->
                            DropdownMenuItem(
                                text = { Text(label) },
                                onClick = { /* Do something... */
                                    onOutputExpanded(false)
                                    onOutputUnit(label)
                                    onOutputConversionFactor(
                                        when(label) {
                                            "Centimeter" -> 0.01
                                            "Meter" -> 1.0
                                            "Kilometer" -> 1000.0
                                            "Feet" -> 0.3048
                                            "Inch" -> 0.0254
                                            "Millimeter" -> 0.001
                                            else -> 0.0
                                        }
                                    )
                                    convertUnits()
                                    onShowResult(true)

                                }
                            )
                        }

                    }
                }
            }

        }
        val resultDisplay =if (showResult) "$outPutvalue $outPutUnit" else ""
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))

        Text(

            text = "Result: $resultDisplay",
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp

        )

    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun convertorUnitPreview() {
    ConvertorUnitTheme {
        ConversionResult()
    }
}