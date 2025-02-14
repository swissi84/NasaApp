package de.syntax_institut.jetpack.a04_05_online_shopper.Views

import android.media.Image
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.SubcomposeAsyncImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import de.syntax_institut.jetpack.a04_05_online_shopper.R
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.Components.ClickableLink
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.Components.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer as YouTubePlayer

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

       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 30.dp),
           horizontalArrangement = Arrangement.Start,
           verticalAlignment = Alignment.CenterVertically
       ) {

           Image(
               painter = painterResource(id = R.drawable.nasalogo),
               contentDescription = "Nasa Logo",
               modifier = Modifier
                   .size(60.dp)
                   .padding(4.dp)
           )
           Spacer(Modifier.padding(horizontal = 10.dp))

           Text(
               text = "Nasa of the Day",
               style = MaterialTheme.typography.headlineLarge
           )
       }

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
            Spacer(Modifier.padding(vertical = 10.dp))

            if ((nasaDaily?.media_type ?: "image") == "video") {
                nasaDaily?.url?.let { it ->
                    homeViewModel.extractYouTubeVideoId(it)?.let {
                        YouTubePlayer(
                            videoId = it
                        )
                    }
                }

                Spacer(Modifier.padding(vertical = 10.dp))

                nasaDaily?.url?.let { ClickableLink(it) }

            } else {
                SubcomposeAsyncImage(
                    model = nasaDaily?.url,
                    contentDescription = "Nasa Image Detail",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .height(250.dp),
                    contentScale = ContentScale.Fit,
                    onLoading = {
                        Log.d("AsyncImage", "Image loading...")
                    },
                    onSuccess = { state ->
                        Log.d("AsyncImage", "Image loaded successfully")
                    },

                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .scale(2f))
                        }
                    },
                    error = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("Error loading Nasa of the Day!")
                            Text("Check your Internet Connection..")
                            Spacer(Modifier.padding(vertical = 6.dp))
                            Icon(
                                painter = painterResource(R.drawable.baseline_error_outline_24),
                                null
                            )
                        }
                    }
                )
            }
            Spacer(Modifier.padding(6.dp))
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

