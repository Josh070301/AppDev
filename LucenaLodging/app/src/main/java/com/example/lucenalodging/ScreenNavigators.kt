package com.example.lucenalodging

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AppNavigator(auth: FirebaseAuth, db : FirebaseFirestore){
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
            LoginAsLandOwner(navController, auth, db)
        }
        composable(route = "LandOwnerCreateAccount"){
            LandOwnerCreateAccount(navController, auth, db)
        }

        composable(route = "LandOwnerCreateAccountSuccess?"){
            LandOwnerCreateAccountSuccess(navController)
        }

        composable(route = "LandOwnerReset"){
            LandOwnerReset(navController)
        }
        composable(route = "LandOwnerResetPassword"){
            LandOwnerResetPassword(navController, auth, db)//returns default string if this variable returned null
        }
        composable(route = "LandOwnerResetSuccess"){
            LandOwnerResetSuccess(navController)
        }
        composable(route = "LandOwnerBrowsePost"){
            WelcomeLandOwner(navController, auth, db)
        }

        composable(route = "LandOwnerMessages"){
                    LandOwnerMessages(navController, auth, db)}
        composable(route = "LandOwnerPost"){
            LandOwnerCreate(navController, auth, db)
        }
        composable(route = "LandOwnerUserProfile"){
            LandOwnerProfile(navController, auth, db)
        }
        composable(route = "LandOwnerSettings") {
            LandOwnerSettings(
                navController = navController, auth, db
            )
        }
        composable(route = "LandOwnerEditPost?documentID={documentID}",
            arguments = listOf(
                navArgument(
                    name = "documentID"
                ){
                    type = NavType.StringType
                }
            )
        ) {backstackEntry ->
            LandOwnerEditPost(navController, auth, db,
                documentID = backstackEntry.arguments?.getString("documentID") ?:"")
        }
        composable(route = "LandOwnerUpdate?documentID={documentID}",
            arguments = listOf(
                navArgument(
                    name = "documentID"
                ){
                    type = NavType.StringType
                }
            )
        ) {
                backstackEntry ->
            LandOwnerUpdate(navController = navController, auth, db,
                documentID = backstackEntry.arguments?.getString("documentID") ?:"")
        }//returns default string if this variable returned null
        composable(route = "LandOwnerUpdatedPost"){
            LandOwnerUpdatedPost(navController, auth, db)
        }
        composable(route = "LandOwnerDelete"){
            LandOwnerDelete(navController, auth, db)
        }
        composable(route = "LandOwnerSingleMessages"){
            LandOwnerSingleMessages(navController, auth, db)
        }
        composable(route = "LandOwnerCreatedPost"){
            LandOwnerCreatedPost(navController, auth, db)
        }
        composable(route = "LandOwnerChangePassword"){
            LandOwnerChangePassword(navController, auth, db)
        }
        composable(route = "LandOwnerConfirmedPassword"){
            LandOwnerConfirmedPassword(navController, auth, db)
        }
        composable(route = "LandOwnerChangedPasswordSuccess"){
            LandOwnerChangedPasswordSuccess(navController, auth, db)
        }

        //Start of tenant routes
        composable(route = "LoginAsTenant"){
            LoginAsTenant(navController)
        }
        composable(route = "TenantCreateAccount"){
            TenantCreateAccount(navController, auth, db)
        }
        composable(route = "TenantCreateAccountSuccess") {
            TenantCreateAccountSuccess(navController, auth, db)
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