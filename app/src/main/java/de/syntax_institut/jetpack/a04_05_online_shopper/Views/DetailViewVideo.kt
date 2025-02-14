package de.syntax_institut.jetpack.a04_05_online_shopper.Views

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.view.WindowInsetsController
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.Components.ExoPlayer
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaDataAssets
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaLinkAssets

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun DetailViewVideo(
    nasaLinkAssets: NasaLinkAssets,
    nasaDataAssets: NasaDataAssets
) {
    val encodedNasaId = nasaDataAssets.nasa_id.replace(" ", "%20")

    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE


    Column(
            modifier = Modifier
                .padding(vertical = 6.dp),
            verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

        ) {

            if (!isLandscape) {
                Spacer(Modifier.padding(vertical = 10.dp))
                Text(
                    text = nasaDataAssets.title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.padding(vertical = 10.dp))
            }

            ExoPlayer(
                videoUrl = "https://images-assets.nasa.gov/video/${encodedNasaId}/${encodedNasaId}~mobile.mp4",

                )

            if (!isLandscape) {
                Spacer(Modifier.padding(vertical = 10.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Text(
                        text = nasaDataAssets.description,
                        textAlign = TextAlign.Center,

                        )
                }
            }
        }
    }

