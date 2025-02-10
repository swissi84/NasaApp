package de.syntax_institut.fakeStore

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import de.syntax_institut.jetpack.a04_05_online_shopper.PreviewProduct
import de.syntax_institut.jetpack.a04_05_online_shopper.Product
import de.syntax_institut.jetpack.a04_05_online_shopper.ProductsViewModel
import de.syntax_institut.jetpack.a04_05_online_shopper.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@Composable
fun HomeView(
    viewModel: ProductsViewModel,
     modifier: Modifier = Modifier
) {

    val products by viewModel.productState.collectAsState()

    Column(
        modifier = modifier,
        ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            ) {

            itemsIndexed(products.reversed()) { index, product ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(16.dp),
                    ) {
                   Column(
                       modifier = Modifier
                           .padding(16.dp),
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       Text(
                           modifier = Modifier
                               .padding(6.dp),
                           text = product.title,
                           style = MaterialTheme.typography.labelLarge

                       )
                       AsyncImage(
                           model = product.image,
                           contentDescription = "Product Image",
                           modifier = Modifier
                               .fillMaxWidth()
                               .height(300.dp),
                           contentScale = ContentScale.Crop
                       )
                       Text(
                           modifier = Modifier
                               .padding(4.dp),
                           text = "Price =  ${product.price}",
                           style = MaterialTheme.typography.bodyLarge

                       )
                   }
                }
            }
        }
    }
}

