package de.syntax_institut.jetpack.a04_05_online_shopper.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import de.syntax_institut.jetpack.a04_05_online_shopper.data.model.HomeViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.SubcomposeAsyncImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.Components.ClickableLink


@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    ) {

    val nasaDaily by homeViewModel.nasaDailyState.collectAsState()



    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Nasa of the Day",
            style = MaterialTheme.typography.headlineLarge
        )
        nasaDaily?.let {
            Text(
                text = it.date,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,


            ) {

            nasaDaily?.let {
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(Modifier.padding(8.dp))

            if ((nasaDaily?.media_type ?: "image") == "video") {
                nasaDaily?.url?.let { it ->
                    homeViewModel.extractYouTubeVideoId(it)?.let {
                        YouTubePlayer(
                            videoId = it
                        )
                    }
                }

                nasaDaily?.url?.let { ClickableLink(it) }

            } else {
                SubcomposeAsyncImage(
                    model = nasaDaily?.url,
                    contentDescription = "Nasa Image Detail",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .height(300.dp),
                    contentScale = ContentScale.Fit,

                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.scale(0.5f))
                        }
                    }
                )
            }

            Spacer(Modifier.padding(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                nasaDaily?.let {
                    Text(
                       text =  it.explanation,
                        textAlign = TextAlign.Center,

                    )
                }
            }
        }
    }
}

@Composable
fun YouTubePlayer(videoId: String) {
    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f) // Startet das Video bei Sekunde 0
                    }
                })
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}