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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LandOwnerProfile(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,userName, usage = "User Profile", userType = "LandOwner")//scaffold on ScaffoldAndEtc.kt
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
            ){
                Row (
                    modifier = Modifier
                        .height(300.dp),
                    verticalAlignment = Alignment.Top
                ){
                    Column (
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "User Profile",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )
                        MainSpacer()// at ScaffoldAndEtc.kt
                        Text(
                            text = "Land Owner",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )
                        Image(painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                        )
                        Row (
                            modifier = Modifier
                                .padding(start = 40.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "Joshua Laude",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp),
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Image(painter = painterResource(id = R.drawable.icons8_create_100),
                                contentDescription = "Edit Icon",
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(30.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        MainSpacer()// at ScaffoldAndEtc.kt
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp, top = 20.dp)
                ){
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ){
                        Row (
                            modifier = Modifier
                                .height(30.dp)
                        ){
                            Text(text = "Email: ", fontWeight = FontWeight.Bold) //soon parameters arguments
                            Text(text = "joshualaude03333@gmail.com")
                        }
                        Row (
                            modifier = Modifier
                                .height(50.dp)
                        ){
                            Text(text = "Total Post: ",fontWeight = FontWeight.Bold)
                            Text(text = "10")
                        }
                    }
                }
            }
        }
    }
}
