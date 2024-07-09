package com.example.lucenalodging

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable //aboutPost now
fun LandOwnerEditPost(navController : NavHostController, auth: FirebaseAuth, db :FirebaseFirestore){
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
    ){
        BottomMenu(navController,fullName, usage ="Browse Post", userType = "LandOwner")
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .height(70.dp)
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 20.dp, top = 20.dp),
                    ) {
                        Row {
                            BackImage(navController = navController, backTo = "LandOwnerBrowsePost" )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier
                                .padding(top = 5.dp)
                        ) {
                            Text(
                                text = "About Post",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            MainSpacer()
                        }
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 20.dp, end = 20.dp)
                    ){
                        Row (
                            modifier = Modifier
                                .height(80.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                                contentDescription ="User Profile",
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                            )
                            Text(text = "Joshua Laude", fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(color = Color(color = 0xFFFAFAFA))
                                .border(
                                    1.dp,
                                    Color.Black,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Text(text = "Images will appear here")
                            }
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 10.dp)
                        ){
                            Row (
                                modifier = Modifier
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.Top
                            ){
                                Column(
                                    modifier = Modifier
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Text(text = "Room for Rent", fontWeight = FontWeight.Bold) //soon to be input parameter from arguments
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(text = "3500PHP Monthly")
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(text = "Location", fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(text = "Purok Happy Valley Enverga Compound Brgy. Ibabang Dupay Lucena City")//soon description input
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = "Curfew", fontWeight = FontWeight.Bold) //soon to be input parameter from arguments
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(text = "2:00PM")
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(text = "Room Includes | 2 Persons Max", fontWeight = FontWeight.Bold) //soon to be input parameter from arguments
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(text = "Free : Water, Wi-Fi, Bed, Foam, 24 Hours CCTV Footage, 24 Hours Water Supply, Locked Gates, Open Parking Available, Own Comfort Room")
                                    Text(text = "Room Requirements", fontWeight = FontWeight.Bold) //soon to be input parameter from arguments
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(text = "1 Month Advance\n1 Month Deposit")
                                    Text(text = "Availability", fontWeight = FontWeight.Bold) //soon to be input parameter from arguments
                                    Spacer(modifier = Modifier.height(3.dp))
                                    Text(text = "Available")
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(60.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ){
                                        OutlinedButton(
                                            onClick = {
                                                navController.navigate("LandOwnerUpdate")
                                            },//soon navigates
                                            modifier = Modifier
                                                .width(150.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398))
                                        ) {
                                            Text(text = "Edit", color = Color.Black)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

