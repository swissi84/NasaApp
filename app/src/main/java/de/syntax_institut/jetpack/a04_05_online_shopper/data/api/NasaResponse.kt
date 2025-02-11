package de.syntax_institut.jetpack.a04_05_online_shopper


data class NasaApiResponse(
    val collection: Collection
)

data class Collection(
    val version: String,
    val href: String,
    val items: List<NasaItem>
)


data class NasaItem(
    val href: String,
    val data: List<NasaData>,
    val links: List<NasaLink>? = null
)


data class NasaData(
    val center: String,
    val dateCreated: String? = null,
    val description: String,
    val description508: String? = null,
    val keywords: List<String>,
    val mediaType: String? = null,
    val nasaId: String? = null,
    val secondaryCreator: String?,
    val title: String
)


data class NasaLink(
    val href: String,
    val rel: String,
    val render: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val size: Int? = null
)