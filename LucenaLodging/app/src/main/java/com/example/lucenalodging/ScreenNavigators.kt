package com.example.lucenalodging

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigator(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "LoginAs"
    ){
        composable(route = "LoginAs"){
            LoginAs(navController)
        }
        composable(route = "LoginAsLandOwner"){
            LoginAsLandOwner(navController)
        }
        composable(route = "BrowsePost?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
            backstackEntry ->
                WelcomeLandOwner(navController = navController,
                    userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "Messages?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
                    LandOwnerMessages(navController = navController,
                       userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "Post?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerCreate(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "UserProfile?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerProfile(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "Settings?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerSettings(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
    }
}