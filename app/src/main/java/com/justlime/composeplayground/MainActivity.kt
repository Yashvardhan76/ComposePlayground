package com.justlime.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.justlime.composeplayground.presentation.screen.HomeScreen
import com.justlime.composeplayground.presentation.screen.PagingScreen
import com.justlime.composeplayground.presentation.screen.QuoteScreen
import com.justlime.composeplayground.ui.theme.ComposePlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePlaygroundTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { paddingValues: PaddingValues ->
                        NavSetup(paddingValues)
                    }
                )
            }
        }
    }
}

@Composable
fun NavSetup(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController, "Quotes Screen") {
        composable("home") {
            HomeScreen(navController, paddingValues)
        }
        composable("Paging Screen") {
            PagingScreen(navController, paddingValues)
        }
        composable("Quotes Screen") {
            QuoteScreen(navController, paddingValues)
        }
    }
}
