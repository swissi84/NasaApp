package de.syntax_institut.fakeStore

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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import de.syntax_institut.jetpack.a04_05_online_shopper.NasaViewModel


@Composable
fun HomeView(
    viewModel: NasaViewModel,
    modifier: Modifier = Modifier
) {
    val nasaData by viewModel.nasaDataState.collectAsState()
    val nasaLink by viewModel.nasaLinksState.collectAsState()

    var search by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }

    val filteredLinks = nasaLink.filter { it.rel == "preview" && it.href.isNotEmpty() }


    Box(
        modifier = Modifier
            .fillMaxSize(),
        ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = GridCells.Fixed(3)
        ) {
            itemsIndexed(filteredLinks) { index, nasaLink ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(4.dp),
                    elevation = CardDefaults.elevatedCardElevation(4.dp)

                ) {
                    AsyncImage(
                        model = nasaLink.href,
                        contentDescription = "Nasa Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
                ) {

                if (isSearchVisible) {
                    TextField(
                        value = search,
                        onValueChange = {
                            search = it.lowercase()
                            viewModel.loadNasaItems(it)
                        },

                        placeholder = { Text("Search..") },
                        modifier = Modifier
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(16.dp),
                    )
                }
                FloatingActionButton(
                    modifier = Modifier
                        .padding(16.dp),
                    onClick = { isSearchVisible = !isSearchVisible },
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
            }

        }
    }
}


