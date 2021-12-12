package com.example.newarchplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newarchplayground.ui.theme.NewArchPlaygroundTheme
import com.example.newarchplayground.ui.components.SearchList
import com.example.newarchplayground.ui.propertydetail.PropertyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val propertyViewModel by viewModels<PropertyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewArchPlaygroundTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("SearchList") }) },
                ){
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        SearchList()
                    }
                }
                // A surface container using the 'background' color from the theme

            }
        }
        propertyViewModel.toString()
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewArchPlaygroundTheme {
        Greeting("Android")
    }
}