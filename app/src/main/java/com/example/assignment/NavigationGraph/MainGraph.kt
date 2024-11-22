package com.example.assignment.NavigationGraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assignment.screen.CameraScreen
import com.example.assignment.screen.FeedsScreen
import com.example.assignment.screen.FoodInfoScreen
import com.example.assignment.screen.HomeScreen
import com.example.assignment.screen.ProfileScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.ACTIVITY) {

        composable(Screen.ACTIVITY){
            HomeScreen()
        }
        composable(Screen.GOALS){
            FoodInfoScreen()
        }
        composable(Screen.CAMERA){
            CameraScreen()
        }
        composable(Screen.FEED){
            FeedsScreen()
        }
        composable(Screen.PROFILE){
            ProfileScreen()
        }
    }
}
