package com.example.lucenalodging

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun LoginAsLandOwner(navController : NavHostController){
    var userName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        Column (

        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(bottomStart = 15.dp)) // 1 side of the border
                    .clip(RoundedCornerShape(bottomEnd = 15.dp))
                    .background(Color(color = 0xFFC2997C)),// color a column or row using background
                verticalAlignment = Alignment.CenterVertically
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Login as Land Owner",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Lucena",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(color = 0xFF674D4D),
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        letterSpacing = 3.sp,
                        textDecoration = TextDecoration.Underline,
                    ) //text styles design
                }
            }
            Row ( // Column for the surface
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, bottom = 40.dp, end = 20.dp)

            ){
                Column( //column for the surface
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(Color(color = 0xFFF8E4BF))
                ) {
                    Column (
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    ){
                        Row (
                            modifier = Modifier
                                .padding(start = 6.dp, top = 10.dp)
                        ){
                            BackImage(navController = navController, backTo="LoginAs")//calls from ScaffoldAndEtc
                        }
                        Row(
                            modifier = Modifier
                                .padding(start = 60.dp, top = 30.dp) // specific paddings
                                .height(50.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_home_208),
                                contentDescription = "Home icon",
                                modifier = Modifier
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(text = "Lodging",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline,
                                    fontStyle = FontStyle.Italic,
                                    letterSpacing = 2.sp
                                )
                            }
                        }
                    }
                    Row( //outLined TextFields
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(190.dp)
                            .padding(top = 10.dp),

                        ) {
                        Column ( //outLined TextFields
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedTextField(
                                value = userName,
                                onValueChange = {userName = it},
                                colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                    unfocusedContainerColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                label = { Text("Username or Email", color = Color.Gray) },
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                            OutlinedTextField(
                                value = password,
                                onValueChange = {password = it},
                                visualTransformation = PasswordVisualTransformation(),
                                colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                    unfocusedContainerColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                                label = { Text("Password", color = Color.Gray) },
                            )

                        }
                    }
                    Column ( // forgot password
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ){
                        Text(text = "Forgot Password?",
                            modifier = Modifier
                                .padding(end = 37.dp)
                                .clickable(
                                    onClick = {
                                        //To be updated
                                    }
                                )
                            ,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                    Column ( //Login button
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .padding(top = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Button(
                            onClick = {
                                navController.navigate("LandOwnerBrowsePost?userName=$userName")
                                      },
                            modifier = Modifier
                                .size(width = 150.dp, height = 45.dp)
                                .border(1.dp, Color.Black, RoundedCornerShape(13.dp)),
                            shape = RoundedCornerShape(13.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(color = 0xFFF2B398),
                            ),
                        ) {
                            Text(text = "Login",
                                color = Color.Black,
                                fontSize = 20.sp,
                                letterSpacing = 2.sp
                            )
                        }
                    }
                    Column ( // sign up colum
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(text = "Don't have a Land Owner Account?")
                        Text(text = "Sign up Here",
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue

                        )
                    }
                }
            }

        }
    }
}