package de.syntax_institut.jetpack.a04_05_online_shopper.Navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import de.syntax_institut.fakeStore.HomeView
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaData
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaDetailView
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaLink
import de.syntax_institut.jetpack.a04_05_online_shopper.NasaViewModel
import de.syntax_institut.jetpack.a04_05_online_shopper.ui.theme.AppTheme
import kotlinx.serialization.Serializable


@Composable
fun AppNavigation(
    viewModel: NasaViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    AppTheme(
//        isDarkMode = isDarkMode
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF13279A), Color(0xFFCED2E1)),
                    )
                )
        ) {

            Scaffold(modifier = Modifier
                .fillMaxSize(),
//Bottombar
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
                            onNavigateToNasaDetailView = { nasaLink ->
                                navController.navigate(
                                    NasaDetailRoute(
                                        href = nasaLink.href,
                                        rel =  nasaLink.rel,

                                    )
                                )
                            }
                        )
                    }

                    composable<NasaDetailRoute> {
                        val nasaDetailRoute = it.toRoute<NasaDetailRoute>()
                            Log.d("MoodDetailRoute", toString())

                       NasaDetailView(
                           nasaLink = NasaLink(
                               href = nasaDetailRoute.href,
                               rel =  nasaDetailRoute.rel,
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
data class NasaDetailRoute(
    val href: String,
    val rel: String,

)


