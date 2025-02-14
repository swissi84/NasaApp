package de.syntax_institut.jetpack.a04_05_online_shopper.data.api

import de.syntax_institut.jetpack.a04_05_online_shopper.NasaData
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaItem
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaLink

data class NasaApiResponseAssets(
    val collection: CollectionAssets
)

data class CollectionAssets(
    val items: List<NasaItemAssets>
)


data class NasaItemAssets(
    val data: List<NasaDataAssets>,
    val links: List<NasaLinkAssets>? = null
)


data class NasaDataAssets(
    val center: String = "",
    val dateCreated: String = "",
    val description: String = "",
    val nasa_id: String,
    val title: String,
    val media_type: String,
)

data class NasaLinkAssets(
    val href: String,
    val rel: String,
)