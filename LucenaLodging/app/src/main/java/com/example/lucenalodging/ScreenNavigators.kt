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
        //start of land owner routes
        composable(route = "LoginAsLandOwner"){
            LoginAsLandOwner(navController)
        }
        composable(route = "LandOwnerCreateAccount"){
            LandOwnerCreateAccount(navController)
        }
        composable(route = "LandOwnerCreatePassword?userName={userName}&fullName={fullName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "fullName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LoginAsLandOwnerCreatePassword(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"",
                fullName = backstackEntry.arguments?.getString("fullName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerCreateAccountSuccess?userName={userName}&fullName={fullName}&password={newPassword}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "fullName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "newPassword"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerCreateAccountSuccess(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"",
                fullName = backstackEntry.arguments?.getString("fullName") ?:"",
                password = backstackEntry.arguments?.getString("newPassword") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerReset"){
            LandOwnerReset(navController)
        }
        composable(route = "LandOwnerResetPassword?userName={userName}&fullName={fullName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "fullName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerResetPassword(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"",
                fullName = backstackEntry.arguments?.getString("fullName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "LandOwnerResetSuccess?userName={userName}&fullName={fullName}&password={newPassword}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "fullName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "newPassword"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LandOwnerResetSuccess(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"",
                fullName = backstackEntry.arguments?.getString("fullName") ?:"",
                password = backstackEntry.arguments?.getString("newPassword") ?:"")//returns default string if this variable returned null
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
        composable(route = "LandOwnerChangePassword?userName={userName}", //reset password logged in
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

        //Start of tenant routes
        composable(route = "LoginAsTenant"){
            LoginAsTenant(navController)
        }
        composable(route = "TenantCreateAccount"){
            TenantCreateAccount(navController)
        }
        composable(route = "TenantCreatePassword?userName={userName}&fullName={fullName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "fullName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            LoginAsTenantCreatePassword(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"",
                fullName = backstackEntry.arguments?.getString("fullName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantCreateAccountSuccess?userName={userName}&fullName={fullName}&password={newPassword}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "fullName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "newPassword"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantCreateAccountSuccess(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"",
                fullName = backstackEntry.arguments?.getString("fullName") ?:"",
                password = backstackEntry.arguments?.getString("newPassword") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantReset"){
            TenantReset(navController)
        }
        composable(route = "TenantResetPassword?userName={userName}&fullName={fullName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "fullName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantResetPassword(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"",
                fullName = backstackEntry.arguments?.getString("fullName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantResetSuccess?userName={userName}&fullName={fullName}&password={newPassword}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "fullName"
                ){
                    type = NavType.StringType
                },
                navArgument(
                    name = "newPassword"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantResetSuccess(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"",
                fullName = backstackEntry.arguments?.getString("fullName") ?:"",
                password = backstackEntry.arguments?.getString("newPassword") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantBrowsePost?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            WelcomeTenant(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantSingleMessages?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantSingleMessages(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantMessages?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantMessages(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantBrowseMore?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantBrowseMore(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantSearch?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            SearchPost(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantUserProfile?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantProfile(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantSettings?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantSettings(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantChangePassword?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantChangePassword(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantConfirmedPassword?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantConfirmedPassword(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }
        composable(route = "TenantChangedPasswordSuccess?userName={userName}",
            arguments = listOf(
                navArgument(
                    name = "userName"
                ){
                    type = NavType.StringType
                }
            )
        ){
                backstackEntry ->
            TenantChangedPasswordSuccess(navController = navController,
                userName = backstackEntry.arguments?.getString("userName") ?:"")//returns default string if this variable returned null
        }

    }
}