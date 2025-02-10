package de.syntax_institut.jetpack.a04_05_online_shopper


data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String,
)


data class PreviewProduct(
    var id: Int,
    var title: String,
    var price: Double,
    var image: Int,
)