package com.example.tweetscategorycompose

import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetscategorycompose.api.TweetsApi
import com.example.tweetscategorycompose.screens.CategoryScreen
import com.example.tweetscategorycompose.screens.DetailScreen
import com.example.tweetscategorycompose.ui.theme.TweetsCategoryComposeTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetsApi: TweetsApi
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetsCategoryComposeTheme {
                Scaffold(topBar = { TopAppBar(title = { Text(text = "Tweets App")})
                }) {
                    Box(modifier = Modifier.padding(it)) {
                        // A surface container using the 'background' color from the theme
                        TweetsApp()
                    }
                }

            }
        }
    }

    @Composable
    fun TweetsApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "category" ){
            composable(route = "category"){
                CategoryScreen{category->
                    navController.navigate("detail/$category")
                }
            }
            composable(route = "detail/{category}", arguments = listOf(navArgument("category"){
                type = NavType.StringType
            })){
                DetailScreen()
            }
            composable(route = "main"){
                TweetsApp()
            }
        }
    }


    @Preview(widthDp = 300, heightDp = 300)
    @Composable
    fun TestComposable() {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.background(Color.Green).fillMaxSize(1f)) {
            Text(text = "Hello")
            Text(text = "Sardar")
            Text(text = "Khan")
        }
    }
}