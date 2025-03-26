package com.example.practica2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{ AppNavegacion() }
    }
}



@Composable
fun AppNavegacion() {
    val navControlador = rememberNavController()
    NavHost(navControlador, startDestination = "uiprincipal") {
        composable("uiprincipal") {
            UIPrincipal(navControlador)
        }
        composable("actividad2/{resultado}") { backStackEntry ->
            val resultado = backStackEntry.arguments?.getString("resultado") ?: "Sin resultado"
            Actividad2(resultado)
        }
    }
}

@Composable
fun UIPrincipal(navControlador: NavController) {
    val contexto = LocalContext.current
    var estatura by rememberSaveable { mutableStateOf("") }
    var peso by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "Calculadora de IMC",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start)
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Color.Gray
        )

        Column(modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)){
            Text(
                text = "Estatura (m)",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = estatura,
                onValueChange = { estatura = it }
            )
        }

        Column(modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)){
            Text(
                text = "Peso (kg)",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it }
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = {
                val estaturaConvertida = estatura.replace(",", ".")
                val eTemp = estaturaConvertida.toFloatOrNull() ?: 0f
                val e = if(eTemp > 3f) eTemp / 100 else eTemp
                val p = peso.toFloatOrNull() ?: 0f

                if (e > 0) {
                    val imc = p / (e * e)

                    if (imc <= 0.9) {
                        Toast.makeText(
                            contexto, "Datos inválidos", Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val clasificacion = when {
                            imc <= 18.4 -> "Bajo peso"
                            imc in 18.5..24.9 -> "Normal"
                            imc in 25.0..29.9 -> "Sobrepeso"
                            imc in 30.0..34.9 -> "Obesidad clase 1"
                            imc in 35.0..39.9 -> "Obesidad clase 2"
                            else -> "Obesidad clase 3"
                        }
                        val resultado = "Tu IMC es: %.1f\nClasificación: %s".format(imc, clasificacion)
                        navControlador.navigate("actividad2/$resultado")
                    }
                } else {
                    Toast.makeText(
                        contexto,"Datos inválidos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White
            )
        ) {
            Text(text = "Calcular")
        }

    }
}




@Composable
fun Actividad2(mensaje: String) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Resultado de tu cálculo:")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = mensaje)
    }
}

@Preview(showBackground = true)
@Composable
fun Previzualizacion() {
    AppNavegacion()
}