package de.syntax_institut.jetpack.a04_05_online_shopper


data class NasaApiResponse(
    val collection: Collection
)

data class Collection(
    val items: List<NasaItem>
)


data class NasaItem(
    val data: List<NasaData>,
    val links: List<NasaLink>? = null
)


data class NasaData(
    val center: String,
    val dateCreated: String = "",
    val description: String,
    val nasa_id: String,
    val secondaryCreator: String = "",
    val title: String,
    val media_type: String,
)

data class NasaLink(
    val href: String,
    val rel: String,
)


data class NasaApiDailyImage(
    val date: String,
    val explanation: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String?,
)