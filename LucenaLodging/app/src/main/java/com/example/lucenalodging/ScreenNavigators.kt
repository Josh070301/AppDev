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
        composable(route = "globalResetPassword"){
            globalResetPassword(navController, auth, db)
        }
        //start of land owner routes
        composable(route = "LoginAsLandOwner"){
            LoginAsLandOwner(navController, auth, db)
        }
        composable(route = "LandOwnerCreateAccount"){
            LandOwnerCreateAccount(navController, auth, db)
        }

        composable(route = "LandOwnerCreateAccountSuccess?email={email}",
            arguments = listOf(
                navArgument(
                    name = "email"
                ){
                    type = NavType.StringType
                }
            )
            )
        {
            backstackentry ->
            LandOwnerCreateAccountSuccess(navController,
                email = backstackentry.arguments?.getString("email") ?: "")
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
        composable(route = "LandOwnerEditUserProfile"){
            LandOwnerEditUserProfile(navController, auth, db)
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
            LoginAsTenant(navController, auth, db)
        }
        composable(route = "TenantCreateAccount"){
            TenantCreateAccount(navController, auth, db)
        }
        composable(route = "TenantCreateAccountSuccess?email={email}",
            arguments = listOf(
                navArgument(
                    name = "email"
                ){
                    type = NavType.StringType
                }
            )
        ) {backstackentry ->
            TenantCreateAccountSuccess(navController, auth, db,
                email = backstackentry.arguments?.getString("email") ?:"")
        }
        composable(route = "TenantBrowsePost"){
            WelcomeTenant(navController, auth, db)
        }
        composable(route = "TenantBrowseMore?documentID={documentID}",
            arguments = listOf(
                navArgument(
                    name = "documentID"
                ){
                    type = NavType.StringType
                }
            )){
            backstackentry ->
            TenantBrowseMore(navController, auth, db,
                documentID = backstackentry.arguments?.getString("documentID") ?:"")
        }
        composable(route = "TenantSingleMessages?landLordUID={uid}",
            arguments = listOf(
                navArgument(
                    name = "uid"
                ){
                    type = NavType.StringType
                }
            )){backstackentry ->
            TenantSingleMessages(navController, auth, db,
                landLordUID = backstackentry.arguments?.getString("uid") ?:"")
        }
        composable(route = "TenantMessages"){
            TenantMessages(navController,auth,db)
        }
        composable(route = "TenantSearch"){
            SearchPost(navController, auth, db)
        }
        composable(route = "TenantUserProfile") {
            TenantProfile(navController,auth, db)
        }
        composable(route = "TenantEditUserProfile") {
            TenantEditUserProfile(navController,auth, db)
        }
        composable(route = "TenantSettings",
        ){

            TenantSettings(navController, auth, db )
        }
        composable(route = "TenantChangePassword"){
            TenantChangePassword(navController, auth, db)//returns default string if this variable returned null
        }
        composable(route = "TenantConfirmedPassword") {
            TenantConfirmedPassword(navController,auth, db)//returns default string if this variable returned null
        }
        composable(route = "TenantChangedPasswordSuccess") {
            TenantChangedPasswordSuccess(navController,auth,db)
        }

    }
}