package com.example.lucenalodging

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LandOwnerChangePassword(navController: NavHostController, userName : String){
    var oldPassword by remember {
        mutableStateOf("")
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,userName, usage = "Settings", userType = "LandOwner")//scaffold on ScaffoldAndEtc.kt
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
                    Column (
                        modifier = Modifier
                            .height(70.dp),
                    ){
                        Row (
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            BackImage(navController = navController, backTo ="LandOwnerSettings?userName=$userName" )
                        }
                    }
                    MainSpacer()// at ScaffoldAndEtc.kt
                    Column (
                        modifier = Modifier
                            .height(400.dp)
                            .fillMaxWidth()
                    ){
                        Row (
                            modifier = Modifier
                                .height(40.dp)
                                .padding(start = 25.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "Please Enter Password to verify it's you",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp),
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Column (
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            OutlinedTextField(
                                value = oldPassword,
                                onValueChange = {oldPassword = it},
                                visualTransformation = PasswordVisualTransformation(),
                                colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                    unfocusedContainerColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                label = { Text("Password", color = Color.Gray) },
                            )
                            Spacer(modifier = Modifier.height(50.dp))
                            Button(
                                onClick = {
                                    navController.navigate("LandOwnerConfirmedPassword?userName=$userName")
                                },
                                modifier = Modifier
                                    .size(width = 150.dp, height = 45.dp)
                                    .border(1.dp, Color.Black, RoundedCornerShape(13.dp)),
                                shape = RoundedCornerShape(13.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(color = 0xFFF2B398),
                                ),
                            ) {
                                Text(
                                    text = "Confirm",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    letterSpacing = 2.sp
                                )
                            }
                        }
                        MainSpacer()
                    }
                }
            }
        }
    }
}

@Composable
fun LandOwnerConfirmedPassword(navController: NavHostController, userName : String){
    var newPassword by remember {
        mutableStateOf("")
    }
    var confirmedPassword by remember {
        mutableStateOf("")
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,userName, usage = "Settings", userType = "LandOwner")//scaffold on ScaffoldAndEtc.kt
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
                    Column (
                        modifier = Modifier
                            .height(70.dp),
                    ){
                        Row (
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            BackImage(navController = navController, backTo ="LandOwnerSettings?userName=$userName" )
                        }
                    }
                    MainSpacer()// at ScaffoldAndEtc.kt
                    Column (
                        modifier = Modifier
                            .height(400.dp)
                            .fillMaxWidth()
                    ){
                        Row (
                            modifier = Modifier
                                .height(40.dp)
                                .padding(start = 25.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "Please Enter Password to verify it's you",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp),
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Column (
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.Start
                        ){
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 30.dp)
                            ){
                                Text(text = "New Password",
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                TextField(
                                    value = newPassword,
                                    onValueChange = {newPassword = it},
                                    visualTransformation = PasswordVisualTransformation(),
                                    colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                        unfocusedContainerColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    label = { Text("Password", color = Color.Gray) },
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(text = "Confirm New Password",
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                TextField(
                                    value = confirmedPassword,
                                    onValueChange = {confirmedPassword = it},
                                    visualTransformation = PasswordVisualTransformation(),
                                    colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                        unfocusedContainerColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    label = { Text("Password", color = Color.Gray) },
                                )
                            }
                            Spacer(modifier = Modifier.height(50.dp))
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Button(
                                    onClick = {
                                        navController.navigate("LandOwnerChangedPasswordSuccess?userName=$userName")
                                    },
                                    modifier = Modifier
                                        .size(width = 150.dp, height = 45.dp)
                                        .border(1.dp, Color.Black, RoundedCornerShape(13.dp)),
                                    shape = RoundedCornerShape(13.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(color = 0xFFF2B398),
                                    ),
                                ) {
                                    Text(
                                        text = "Confirm",
                                        color = Color.Black,
                                        fontSize = 20.sp,
                                        letterSpacing = 2.sp
                                    )
                                }
                            }
                        }
                        MainSpacer()
                    }
                }
            }
        }
    }
}
@Composable
fun LandOwnerChangedPasswordSuccess(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController, userName, usage = "Post", userType = "LandOwner")
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
                            .height(70.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Change Password",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp),
                            )
                            MainSpacer()
                        }
                    }
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
                            Text(text = "Password Change Successful",
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center
                            )
                            OutlinedButton(
                                onClick = {
                                    navController.navigate("LandOwnerSettings?userName=$userName")
                                },//soon navigates
                                colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398)),
                                modifier = Modifier
                                    .width(200.dp)
                            ) {
                                Text(text = "Back to Settings", color = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }
}


