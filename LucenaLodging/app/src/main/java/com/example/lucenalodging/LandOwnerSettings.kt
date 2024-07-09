package com.example.lucenalodging

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun LandOwnerSettings(navController: NavHostController, auth: FirebaseAuth, db : FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember{
        mutableStateOf("")
    }
    if (uid != null){
        db.collection("LandLords").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    val FullNameFromDB = document.getString("fullName")
                    fullName = FullNameFromDB.toString()
                }
            }
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,fullName, usage = "Settings", userType = "LandOwner")//scaffold on ScaffoldAndEtc.kt
        Row ( // Column for the surface
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, bottom = 125.dp, end = 5.dp, top = 70.dp) //padding in top and bottom bar

        ) {
            Column( //column for the surface
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(Color(color = 0xFFF8E4BF))
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp)
                ){
                    Row (
                        modifier = Modifier
                            .height(70.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column (
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text = "Settings",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp),
                            )
                        }
                    }
                    MainSpacer()// at ScaffoldAndEtc.kt
                    Column (
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    ){
                        Row (
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerChangePassword")
                                    }
                                )
                                .height(40.dp)
                                .padding(start = 25.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = "Change Password",
                                fontSize = 20.sp
                            )
                        }
                        MainSpacer()// at ScaffoldAndEtc.kt
                        Row (
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        navController.navigate("LoginAs")
                                    }
                                )
                                .height(40.dp)
                                .padding(start = 25.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = "Log Out",
                                fontSize = 20.sp
                            )
                        }
                        MainSpacer()
                    }
                }
            }
        }
    }
}
