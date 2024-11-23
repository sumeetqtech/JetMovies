package com.example.jetmovieapp.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.jetmovieapp.R
import com.example.jetmovieapp.model.Movie
import com.example.jetmovieapp.model.getMovies

@Preview
@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movie: Movie = getMovies()[0],
    onClick: (String) -> Unit = {}
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClick.invoke(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RoundedCornerShape(4.dp), shadowElevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .listener(
                            onStart = { println("Image loading started") },
                            onError = { _, throwable -> println("Error: $throwable") },
                            onSuccess = { _, _ -> println("Image loaded successfully") }
                        )
                        .crossfade(true)
                        //.transformations(CircleCropTransformation()) // optional
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.FillBounds,
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.DarkGray)) {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                )
                            ) {
                                append("Director: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append(movie.director)
                            }
                        }
                    },
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.DarkGray)) {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                )
                            ) {
                                append("Released: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append(movie.year)
                            }
                        }
                    },
                    style = MaterialTheme.typography.bodySmall
                )

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                )
                            ) {
                                append("Plot: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontWeight = FontWeight.Light
                                )
                            ) {
                                append(movie.plot)
                            }
                        }, style = MaterialTheme.typography.bodySmall)

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.DarkGray)) {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                        )
                                    ) {
                                        append("Actors: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                            fontWeight = FontWeight.Light
                                        )
                                    ) {
                                        append(movie.actors)
                                    }
                                }
                            },
                            style = MaterialTheme.typography.bodySmall
                        )

                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.DarkGray)) {
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                        )
                                    ) {
                                        append("Rating: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                            fontWeight = FontWeight.Light
                                        )
                                    ) {
                                        append(movie.rating)
                                    }
                                }
                            },
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Dropdown Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        }
                )
            }
        }
    }
}
