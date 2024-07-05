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
        composable(route = "LandOwnerBrowsePost?userName={userName}",
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
        composable(route = "LandOwnerMessages?userName={userName}",
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
        composable(route = "LandOwnerPost?userName={userName}",
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
        composable(route = "LandOwnerUserProfile?userName={userName}",
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
        composable(route = "LandOwnerSettings?userName={userName}",
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
        composable(route = "LandOwnerEditPost?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerEditPost(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerUpdate?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerUpdate(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerUpdatedPost?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerUpdatedPost(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerDelete?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerDelete(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerSingleMessages?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerSingleMessages(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerCreatedPost?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerCreatedPost(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerChangePassword?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerChangePassword(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerConfirmedPassword?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerConfirmedPassword(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerChangedPasswordSuccess?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerChangedPasswordSuccess(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
    }
}