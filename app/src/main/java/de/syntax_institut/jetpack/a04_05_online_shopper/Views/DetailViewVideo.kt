package de.syntax_institut.jetpack.a04_05_online_shopper.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.Components.ExoPlayer
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaDataAssets
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaLinkAssets

@Composable
fun DetailViewVideo(
    nasaLinkAssets: NasaLinkAssets,
    nasaDataAssets: NasaDataAssets
) {

    val encodedNasaId = nasaDataAssets.nasa_id.replace(" ", "%20")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

                Text(nasaDataAssets.title)
                Spacer(modifier = Modifier.height(16.dp))


            ExoPlayer(
                videoUrl = "https://images-assets.nasa.gov/video/${encodedNasaId}/${encodedNasaId}~mobile.mp4",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
                Text("Test")
                Spacer(modifier = Modifier.height(16.dp))
                Text(nasaDataAssets.nasa_id)
                Spacer(modifier = Modifier.height(16.dp))
                Text(encodedNasaId)

        }
    }
}