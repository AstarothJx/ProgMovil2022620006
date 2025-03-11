package com.example.botones

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.botones.ui.theme.BotonesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { UIPrincipal() }
    }
}

@Composable
fun UIPrincipal() {
    var cadTxtOp1 by remember { mutableStateOf("") }
    var cadTxtOp2 by remember { mutableStateOf("") }
    var cadTxtRes by remember { mutableStateOf("") }

    fun btnLimpiar_Click() {
        cadTxtOp1 = ""
        cadTxtOp2 = ""
        cadTxtRes = ""
    }

    fun btnSumar_Click() {
        val op1 = cadTxtOp1.toIntOrNull() ?: 0 //devuelve 0 si es nulo
        val op2 = cadTxtOp2.toIntOrNull() ?: 0
        cadTxtRes =(op1+op2).toString()
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp), Arrangement.Top, Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
            Text("Op1", Modifier.weight(1f))
            Text("Op2", Modifier.weight(1f))
            Text("Res", Modifier.weight(1f))
        }
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
            TextField(value = cadTxtOp1, onValueChange = { cadTxtOp1 = it }, Modifier.weight(1f))
            TextField(value = cadTxtOp2, onValueChange = { cadTxtOp2 = it }, Modifier.weight(1f))
            TextField(value = cadTxtRes, onValueChange = { cadTxtRes = it }, Modifier.weight(1f))
        }
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
            Button(onClick = { btnLimpiar_Click()}, Modifier.weight(1.5f)) {
                Text("Limpiar")
            }
            Button(onClick = { btnSumar_Click()}, Modifier.weight(1.5f)) {
                Text("Sumar")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Previzualizacion() {
    UIPrincipal()
}
