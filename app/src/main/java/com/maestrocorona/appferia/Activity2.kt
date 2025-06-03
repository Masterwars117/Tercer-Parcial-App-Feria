package com.maestrocorona.appferia

// Importamos las librerías necesarias para trabajar con Jetpack Compose y la actividad
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color

// Esta es una actividad que usamos para mostrar el concierto o artista de la feria
class Activity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cargamos la pantalla usando Jetpack Compose
        setContent {
            // Llamamos a nuestra pantalla composable y pasamos una función para cerrar la actividad
            ConciertoScreen(onBackPressed = { finish() })
        }
    }
}

// Esta función representa la UI (interfaz) de la pantalla del concierto
@Composable
fun ConciertoScreen(onBackPressed: () -> Unit) {
    // Surface es como el fondo base de la pantalla
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background // Usa el color de fondo del tema
    ) {
        // Usamos un Column para colocar los elementos uno debajo del otro
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Añadimos espacio alrededor
            horizontalAlignment = Alignment.CenterHorizontally, // Centramos horizontalmente
            verticalArrangement = Arrangement.SpaceBetween // Espaciamos elementos de arriba a abajo
        ) {
            // Un pequeño espacio al inicio para no estar pegado arriba
            Spacer(modifier = Modifier.height(32.dp))

            // Mostramos la imagen del artista o concierto
            Image(
                painter = painterResource(id = R.drawable.artista_feria), // Imagen ubicada en res/drawable
                contentDescription = "Artista en concierto",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp) // Altura de la imagen
            )

            // Botón que permite volver a la pantalla principal (MainActivity)
            Button(
                onClick = onBackPressed, // Llama a finish() para cerrar esta pantalla
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Regresar a la feria",
                    fontSize = 16.sp
                )
            }
        }
    }
}