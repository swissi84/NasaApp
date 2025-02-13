package de.syntax_institut.jetpack.a04_05_online_shopper.Navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

import de.syntax_institut.fakeStore.ImageView
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaData
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.DetailViewImage
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaLink
import de.syntax_institut.jetpack.a04_05_online_shopper.ImageViewModel
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.Components.FullImageBackground
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.DetailViewVideo
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.HomeView
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.VideoView
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaDataAssets
import de.syntax_institut.jetpack.a04_05_online_shopper.data.api.NasaLinkAssets
import de.syntax_institut.jetpack.a04_05_online_shopper.data.model.HomeViewModel
import de.syntax_institut.jetpack.a04_05_online_shopper.data.model.VideoViewModel
import de.syntax_institut.jetpack.a04_05_online_shopper.ui.theme.AppTheme
import kotlinx.serialization.Serializable


@SuppressLint("SuspiciousIndentation")
@Composable
fun AppNavigation(
    homeViewModel: HomeViewModel = viewModel(),
    imageViewModel: ImageViewModel = viewModel(),
    videoViewModel: VideoViewModel = viewModel(),
) {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    AppTheme(
//        isDarkMode = isDarkMode
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            FullImageBackground()

            Scaffold(modifier = Modifier
                .fillMaxSize(),

                bottomBar = {
                    NavigationBar(
                        modifier = Modifier,
                        containerColor = Color.Transparent,
                        tonalElevation = 5.dp
                    )
                    {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        NavItem.entries.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = currentBackStackEntry?.destination?.hasRoute(item.route::class)
                                    ?: false,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.label
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.label,
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold

                                    )

                                },

                                )
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = HomeView,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(innerPadding)

                ) {

                    composable<HomeView> {
                        HomeView(
                            homeViewModel = homeViewModel
                        )
                    }

                    composable<ImageView> {
                        ImageView(
                            imageViewModel = imageViewModel,
                            onNavigateToDetailViewImage = { nasaLink, nasaData ->
                                navController.navigate(
                                    NasaDetailRouteImage(
                                        href = nasaLink.href,
                                        rel = nasaLink.rel,
                                        title = nasaData.title,
                                        center = nasaData.center,
                                        dateCreated = nasaData.dateCreated,
                                        description = nasaData.description,
                                        nasa_id = nasaData.nasa_id,
                                        secondaryCreator = nasaData.secondaryCreator,
                                        media_item = nasaData.media_type,
                                    )
                                )
                            },
                            )
                    }

                    composable<VideoView> {
                        VideoView(
                            videoViewModel = videoViewModel,
                            onNavigateToDetailViewVideo =  { nasaLink, nasaData ->
                                navController.navigate(
                                    NasaDetailRouteVideo(
                                        href = nasaLink.href,
                                        rel = nasaLink.rel,
                                        title = nasaData.title,
                                        center = nasaData.center,
                                        dateCreated = nasaData.dateCreated,
                                        description = nasaData.description,
                                        nasa_id = nasaData.nasa_id,
                                        media_item = nasaData.media_type,
                                    )
                                )
                            },

                        )
                    }

                    composable<NasaDetailRouteImage> {
                        val nasaDetailRouteImage = it.toRoute<NasaDetailRouteImage>()
                        Log.d("MoodDetailRoute", toString())

                        DetailViewImage(
                            nasaLink = NasaLink(
                                href = nasaDetailRouteImage.href,
                                rel = nasaDetailRouteImage.rel,
                            ),
                            nasaData = NasaData(
                                title = nasaDetailRouteImage.title,
                                center = nasaDetailRouteImage.center,
                                dateCreated = nasaDetailRouteImage.dateCreated,
                                description = nasaDetailRouteImage.description,
                                nasa_id = nasaDetailRouteImage.nasa_id,
                                secondaryCreator = nasaDetailRouteImage.secondaryCreator,
                                media_type = nasaDetailRouteImage.media_item,
                            )
                        )
                    }

                    composable<NasaDetailRouteVideo> {
                        val nasaDetailRouteVideo = it.toRoute<NasaDetailRouteVideo>()
                        Log.d("MoodDetailRoute", toString())

                        DetailViewVideo(
                            nasaLinkAssets = NasaLinkAssets(
                                href = nasaDetailRouteVideo.href,
                                rel = nasaDetailRouteVideo.rel,
                            ),
                            nasaDataAssets = NasaDataAssets(
                                title = nasaDetailRouteVideo.title,
                                center = nasaDetailRouteVideo.center,
                                dateCreated = nasaDetailRouteVideo.dateCreated,
                                description = nasaDetailRouteVideo.description,
                                nasa_id = nasaDetailRouteVideo.nasa_id,
                                media_type = nasaDetailRouteVideo.media_item,
                            )
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object HomeView

@Serializable
object ImageView

@Serializable
object VideoView

@Serializable
data class NasaDetailRouteImage(
    val href: String,
    val rel: String,
    val title: String,
    val center: String,
    val dateCreated: String,
    val description: String,
    val nasa_id: String,
    val secondaryCreator: String,
    val media_item: String,

)

@Serializable
data class NasaDetailRouteVideo(
    val href: String,
    val rel: String,
    val title: String,
    val center: String,
    val dateCreated: String,
    val description: String,
    val nasa_id: String,
    val media_item: String,

    )



enum class NavItem(
    val route: Any,
    val label: String,
    val icon: ImageVector,
) {
    First(HomeView, "Home", Icons.Filled.Home),
    Second(ImageView, "Image", Icons.Filled.Image),
    Third(VideoView, "Video", Icons.Filled.VideoLibrary),
}
