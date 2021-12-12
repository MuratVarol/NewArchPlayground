package com.example.newarchplayground.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.newarchplayground.MainViewModel
import com.example.newarchplayground.data.PropertyData
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchList() {
    Column(modifier = Modifier.padding(2.dp)) {
        val vm: MainViewModel = hiltViewModel()
        MovieList(movies = vm.getSearch())
    }
}

@Composable
fun MovieList(movies: Flow<PagingData<PropertyData>>) {
    val lazyMovieItems: LazyPagingItems<PropertyData> = movies.collectAsLazyPagingItems()

    LazyColumn {
        this.items(lazyMovieItems) { data ->
            PropertyItem(propertyData = data!!)
        }

        lazyMovieItems.apply {
            when{
                loadState.refresh is LoadState.Loading->{
                    item { Row( modifier = Modifier.fillMaxWidth()
                        .background(Color.Green),

                    verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                        ) {
                        CircularProgressIndicator(modifier = Modifier)
                    } }
                }

                loadState.append is LoadState.Loading->{
                    item { Row( modifier = Modifier.fillMaxWidth()
                        .background(Color.Yellow),

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier)
                    } }
                }
            }
        }
    }
}


@Composable
fun PropertyItem(propertyData: PropertyData) {
    Card(elevation = 16.dp, modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PropertyImage(propertyData.imgUrl)
            Text(text = propertyData.name,Modifier.padding(start=8.dp), style = MaterialTheme.typography.body1)
        }
    }


}

@Composable
fun PropertyImage(imageUrl: String) {
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            transformations(CircleCropTransformation())
        })
    Image(painter = painter, contentDescription = "", modifier = Modifier.size(120.dp))
}
