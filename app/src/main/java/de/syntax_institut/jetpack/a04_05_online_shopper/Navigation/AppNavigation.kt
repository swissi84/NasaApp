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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
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
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.NasaDetailView
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaLink
import de.syntax_institut.jetpack.a04_05_online_shopper.ImageViewModel
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.Components.FullImageBackground
import de.syntax_institut.jetpack.a04_05_online_shopper.Views.HomeView
import de.syntax_institut.jetpack.a04_05_online_shopper.data.model.HomeViewModel
import de.syntax_institut.jetpack.a04_05_online_shopper.ui.theme.AppTheme
import kotlinx.serialization.Serializable


@SuppressLint("SuspiciousIndentation")
@Composable
fun AppNavigation(
    HomeViewModel: HomeViewModel = viewModel(),
    ImageViewModel: ImageViewModel = viewModel(),
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
                            homeViewModel = HomeViewModel
                        )
                    }

                    composable<ImageView> {
                        ImageView(
                            onNavigateToNasaDetailView = { nasaLink, nasaData ->
                                navController.navigate(
                                    NasaDetailRoute(
                                        href = nasaLink.href,
                                        rel = nasaLink.rel,
                                        title = nasaData.title,
                                        center = nasaData.center,
                                        dateCreated = nasaData.dateCreated,
                                        description = nasaData.description,
                                        nasa_Id = nasaData.nasa_Id,
                                        secondaryCreator = nasaData.secondaryCreator
                                    )
                                )
                            },
                            ImageViewModel = ImageViewModel,

                            )
                    }

                    composable<NasaDetailRoute> {
                        val nasaDetailRoute = it.toRoute<NasaDetailRoute>()
                        Log.d("MoodDetailRoute", toString())

                        NasaDetailView(
                            nasaLink = NasaLink(
                                href = nasaDetailRoute.href,
                                rel = nasaDetailRoute.rel,
                            ),
                            nasaData = NasaData(
                                title = nasaDetailRoute.title,
                                center = nasaDetailRoute.center,
                                dateCreated = nasaDetailRoute.dateCreated,
                                description = nasaDetailRoute.description,
                                nasa_Id = nasaDetailRoute.nasa_Id,
                                secondaryCreator = nasaDetailRoute.secondaryCreator,
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
data class NasaDetailRoute(
    val href: String,
    val rel: String,
    val title: String,
    val center: String,
    val dateCreated: String?,
    val description: String,
    val nasa_Id: String?,
    val secondaryCreator: String?

)

enum class NavItem(
    val route: Any,
    val label: String,
    val icon: ImageVector,
) {
    First(HomeView, "Home", Icons.Filled.Home),
    Second(ImageView, "Image", Icons.Filled.Image),
}
