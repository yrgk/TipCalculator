package com.example.tipcalculator

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                TipCalculatorTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val amountInput = remember { mutableStateOf("") }
                        var tipcount = amountInput.value.toDoubleOrNull() ?: 0.0


                        val percent = remember { mutableStateOf("") }
                        val perc = percent.value.toDoubleOrNull() ?: 0.0


                        var roundUp = remember { mutableStateOf(false) }
                        var roundChanged = remember { mutableStateOf(true) }


                        var nottotal = tipcount + tipcount * perc / 100
                        var totaltip = (nottotal * 10000.0).roundToInt() / 10000.0


                        if(roundUp.value) {
                            totaltip = ceil(totaltip)
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()

                            ,
                            contentAlignment = Alignment.Center
                        ) {


                            Column(
                                modifier = Modifier
                                    .padding(40.dp)
                                    .verticalScroll(rememberScrollState())


                            ) {
                                Text(text = "Tip Calculator", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(20.dp))

                                TextField(
                                    value = amountInput.value,
                                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.money), null) },
                                    onValueChange = { amountInput.value = it },
                                    label = { Text(stringResource(R.string.app_name)) },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 20.dp)
                                )

                                TextField(
                                    value = percent.value,
                                    leadingIcon = { Icon(painter = painterResource(id = R.drawable.percent), null) },
                                    onValueChange = { percent.value = it },
                                    label = { Text("Percentage") },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Done
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 20.dp)
                                )


                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(48.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = stringResource(R.string.round_up_tip),
                                        modifier = Modifier.padding(end = 20.dp)
                                    )

                                    Switch(
                                        checked = roundUp.value,
                                        onCheckedChange = { roundUp.value = it },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(Alignment.End)
                                    )
                                }

                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Tip Amount: $ ${totaltip}",
                                        style = MaterialTheme.typography.displaySmall
                                    )
                                }


                            }
                        }
                    }
                }


        }
    }
}

