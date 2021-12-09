package com.example.newarchplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.newarchplayground.ui.AppScreen
import com.example.newarchplayground.ui.propertydetail.PropertyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val propertyViewModel by viewModels<PropertyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
        propertyViewModel.toString()
    }
}
