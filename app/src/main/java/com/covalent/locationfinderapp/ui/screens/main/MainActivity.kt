package com.covalent.locationfinderapp.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.covalent.locationfinderapp.ui.screens.deliveryApp.HomeScreen
import com.covalent.locationfinderapp.ui.screens.location.LocationScreen
import com.covalent.locationfinderapp.ui.theme.LocationFinderAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.what3words.androidwrapper.What3WordsV3
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocationFinderAppTheme {
               SetStatusColor()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocationScreen()
//                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LocationFinderAppTheme {
        Greeting("Android")
    }
}

@Composable
fun SetStatusColor() {
    val systemUiController = rememberSystemUiController()
    SideEffect{
        systemUiController.setStatusBarColor(Color(0xFF263238))
    }
}