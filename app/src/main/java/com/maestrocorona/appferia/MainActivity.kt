package com.maestrocorona.appferia

// Importamos todo lo necesario para trabajar con Jetpack Compose y actividades
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

// Esta es nuestra actividad principal (la pantalla que aparece al iniciar la app)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Esto hace que nuestra app use toda la pantalla, quitando los bordes del sistema
        enableEdgeToEdge()

        // Aquí cargamos el contenido de la pantalla usando Jetpack Compose
        setContent {
            // Llamamos a MainScreen y le pasamos dos funciones que abren nuevas pantallas
            MainScreen(
                onConciertoClick = { startActivity(Intent(this, Activity2::class.java)) },
                onFechasClick = { startActivity(Intent(this, CatGalleryActivity::class.java)) } // Abre la pantalla de fechas importantes
            )
        }
    }
}

// Color morado personalizado que se usa en las tarjetas
private val Purple40 = Color(0xFF6650a4)

// Esta es la función que dibuja la pantalla principal
@Composable
fun MainScreen(
    onConciertoClick: () -> Unit, // función que se ejecuta al presionar el botón de concierto
    onFechasClick: () -> Unit     // función que se ejecuta al presionar el botón de la galería de gatitos
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Usamos un Column para organizar los elementos verticalmente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mostramos 3 tarjetas con información de naves
            BusinessCardWithDialog("Nave 1", R.drawable.logo_rest, R.drawable.nave1)
            BusinessCardWithDialog("Nave 2", R.drawable.logo_rest, R.drawable.nave2)
            BusinessCardWithDialog("Nave 3", R.drawable.logo_rest, R.drawable.nave3)

            // Tarjeta especial para el concierto/artista
            ConciertoCard("Artista", R.drawable.logo_rest, onClick = onConciertoClick)

            // Espacio antes del botón final
            Spacer(modifier = Modifier.height(32.dp))

            // Botón para la galería de gatitos
            Button(
                onClick = onFechasClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Galería de Gatitos", fontFamily = FontFamily.SansSerif)
            }
        }
    }
}


// Esta función dibuja una tarjeta (Card) con botón que muestra un diálogo con info e imagen
@Composable
fun BusinessCardWithDialog(title: String, logoResId: Int, imageResId: Int) {
    // Esta variable controla si el diálogo está visible o no
    var showDialog by remember { mutableStateOf(false) }

    // Si showDialog es true, se muestra el AlertDialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false }, // se cierra al tocar fuera
            title = {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Mostramos la imagen de la nave
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = "Imagen $title",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Texto de ejemplo (puede ser cambiado por información real)
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla facilisi. " +
                                "Suspendisse potenti. Curabitur eu sem at libero tristique blandit.",
                        textAlign = TextAlign.Justify,
                        fontSize = 14.sp
                    )
                }
            },
            confirmButton = {} // No tiene botón, se cierra tocando fuera
        )
    }

    // Esta es la tarjeta que contiene imagen + texto + botón "Info"
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Purple40,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Parte izquierda: logo e información
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = logoResId),
                    contentDescription = "Logo",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }

            // Botón que activa el diálogo al hacer clic
            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Purple40
                )
            ) {
                Text("Info")
            }
        }
    }
}

// Esta tarjeta es especial para el artista/concierto
@Composable
fun ConciertoCard(title: String, imageResId: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Purple40,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Parte izquierda: imagen + título
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Imagen",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }

            // Botón que lleva a la pantalla del concierto
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Purple40
                )
            ) {
                Text("Entrar")
            }
        }
    }
}

// Esta función es solo para mostrar una vista previa de la pantalla en el editor de Android Studio
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(
        onConciertoClick = {},
        onFechasClick = {}
    )
}

