package com.example.jetmovieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetmovieapp.components.MovieRow
import com.example.jetmovieapp.model.Movie
import com.example.jetmovieapp.model.getMovies
import com.example.jetmovieapp.navigation.MovieScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Magenta
            ),
            title = {
                Text(
                    text = "Movies"
                )
            },
        )
    }) { paddingValues ->
        MainContent(
            navController = navController,
            padding = paddingValues
        )
    }
}

@Composable
fun MainContent(
    navController: NavController,
    padding: PaddingValues,
    movieList: List<Movie> = getMovies()
) {
    Surface(
        modifier = Modifier.padding(padding),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            LazyColumn {
                items(items = movieList) {
                    MovieRow(it) { movie ->
                        navController.navigate(route = MovieScreens.DetailsScreen.name + "/${movie}")
                    }
                }
            }
        }
    }
}
