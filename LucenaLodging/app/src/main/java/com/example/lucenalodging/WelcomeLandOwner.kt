package com.example.lucenalodging

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun WelcomeLandOwner(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ){
        BottomMenu(navController,userName, usage ="Browse Post", userType = "LandOwner")//scaffold on ScaffoldAndEtc.kt
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
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Your Posts",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    repeat(10){
                        userContent(navController,userName,userType = "LandOwner") //calls user Content can be multiple dependin on count
                    }
                }
            }
        }
    }
}
