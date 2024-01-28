package com.example.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    var coins by remember { mutableIntStateOf(0) }
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "menu-view"
        ) {
            composable("menu-view") {
                MenuView(coins=coins) {
                    navController.navigate("game-scene")
                }
            }
            composable("game-scene") {
                GameScene(
                    onCoinsChanged = { newCoins -> coins = newCoins },
                    function = {
                        navController.navigate("end-scene")
                    }
                )
            }
            composable("end-scene") {
                EndScene(coins) { newCoins ->
                    coins = newCoins
                    navController.navigate("menu-view")
                }
            }
        }
    }