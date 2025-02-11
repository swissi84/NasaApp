package de.syntax_institut.jetpack.a04_05_online_shopper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import de.syntax_institut.fakeStore.HomeView
import de.syntax_institut.jetpack.a04_05_online_shopper.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF13279A), Color(0xFFCED2E1)),
                            )
                        )
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    HomeView(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel()
                    )
                }
            }
        }
        }
    }
}
