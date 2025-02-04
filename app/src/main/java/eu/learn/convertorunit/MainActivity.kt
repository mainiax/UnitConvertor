package eu.learn.convertorunit

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.learn.convertorunit.ui.theme.ConvertorUnitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConvertorUnitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    ConvertorUnit(Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun ConvertorUnit(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Convertor Unit",
            modifier = modifier
        )
        Spacer(modifier = Modifier.padding(10.dp))
        OutlinedTextField(value = "", onValueChange = {
            //
        })
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        Row {
            val context = LocalContext.current
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Convert"    ,modifier = modifier.padding(horizontal = 10.dp , vertical = 2.dp))

                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            Spacer(modifier = Modifier.padding(vertical = 40.dp))

            Button(onClick = { /*TODO*/
                var text = "Hello toast!"
//                val toast = Toast.makeText(context, text, LENGTH_SHORT )
//                toast.show()
            }) {
                Text(text = "Comb" ,modifier = modifier.padding(horizontal = 10.dp , vertical = 2.dp) )

                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }

        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        Text(
            text = "Result",
            modifier = modifier
        )

    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun convertorUnitPreview() {
    ConvertorUnitTheme {
      ConvertorUnit()
    }
}