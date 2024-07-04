package com.example.lucenalodging

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun LandOwnerDelete(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController, userName, usage = "Browse Post", userType = "LandOwner")
        Row( // Column for the surface
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 5.dp,
                    bottom = 125.dp,
                    end = 5.dp,
                    top = 70.dp
                ) //padding in top and bottom bar

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
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(text = "Post Successfully Deleted",
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center
                            )
                            OutlinedButton(
                                onClick = {
                                    navController.navigate("LandOwnerBrowsePost?userName=$userName")
                                },//soon navigates
                                colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398)),
                                modifier = Modifier
                                    .width(150.dp)
                            ) {
                                Text(text = "Check My Posts", color = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }
}