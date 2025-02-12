package de.syntax_institut.jetpack.a04_05_online_shopper.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaData
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaLink
import de.syntax_institut.jetpack.a04_05_online_shopper.ImageViewModel

@Composable
fun NasaDetailView(
    nasaLink: NasaLink,
    nasaData: NasaData,
    ) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(nasaLink.rel)
            Text(nasaData.title)

            SubcomposeAsyncImage(
                model = nasaLink.href,
                contentDescription = "Nasa Image Detail",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit,
                loading = { CircularProgressIndicator(
                    Modifier.scale(.5f)
                )
                }
            )
        }
    }
}