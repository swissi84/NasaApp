package de.syntax_institut.jetpack.a04_05_online_shopper.Views

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaData
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaLink

@Composable
fun DetailViewImage(
    nasaLink: NasaLink,
    nasaData: NasaData,
) {

    val zoomState = remember { mutableStateOf(1f) }
    val panState = remember { mutableStateOf(Offset(0f, 0f)) }

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

            Text(nasaData.title)

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->

                            zoomState.value = zoomState.value * zoom
                            panState.value = panState.value + pan
                        }
                    },
                contentAlignment = Alignment.Center
            ) {

                SubcomposeAsyncImage(
                    model = nasaLink.href,
                    contentDescription = "Nasa Image Detail",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .graphicsLayer(
                            scaleX = zoomState.value,
                            scaleY = zoomState.value,
                            translationX = panState.value.x,
                            translationY = panState.value.y
                        ),
                    contentScale = ContentScale.Fit,
                    )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}