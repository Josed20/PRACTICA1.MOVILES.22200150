package dev.josed20.practica1moviles22200150

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dev.josed20.practica1moviles22200150.navigate.AppNavGraph
import dev.josed20.practica1moviles22200150.ui.theme.PRACTICA1MOVILES22200150Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PRACTICA1MOVILES22200150Theme {
                val nav = rememberNavController()
                AppNavGraph(nav)
            }
        }
    }
}