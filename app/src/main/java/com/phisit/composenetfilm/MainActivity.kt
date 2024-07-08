package com.phisit.composenetfilm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.phisit.composenetfilm.home.HomeScreen
import com.phisit.composenetfilm.moviedetail.MovieDetailScreen
import com.phisit.composenetfilm.search.SearchScreen
import com.phisit.composenetfilm.ui.theme.ComposeNetFilmTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposeNetFilmTheme {
                Scaffold { innerPadding ->
                    NavigationGraph(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavigationGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeRouteScreen) {
        composable<HomeRouteScreen> {
            HomeScreen(navController, modifier)
        }
        composable<SearchRouteScreen> {
            SearchScreen(navController, modifier)
        }
        composable<MovieDetailRouteScreen>(
            typeMap = MovieDetailRouteScreen.type
        ) {
          /*  val movie = it.toRoute<MovieDetailRouteScreen>().movieModel
            Log.e("Movie Screen", movie.toString())*/
            MovieDetailScreen(
                navController = navController,
                modifier = modifier
            )
        }
    }
}
