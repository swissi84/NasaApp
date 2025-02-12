package de.syntax_institut.jetpack.a04_05_online_shopper.Views

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import de.syntax_institut.jetpack.a04_05_online_shopper.data.model.HomeViewModel


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
            text = "Picture of the Day",
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,


            ) {

            nasaDaily?.let {
                Text(
                    text = it.title,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(Modifier.padding(16.dp))

            nasaDaily?.let { Text(it.explanation) }

        }
    }
}