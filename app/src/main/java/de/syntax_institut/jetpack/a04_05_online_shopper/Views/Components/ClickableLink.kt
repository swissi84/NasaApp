package de.syntax_institut.jetpack.a04_05_online_shopper.Views.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun ClickableLink(link: String) {
    val uriHandler = LocalUriHandler.current

    Text(
        text = link,
        style = MaterialTheme.typography.subtitle2 ,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                uriHandler.openUri(link)
            }
    )
}