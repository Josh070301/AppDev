package com.example.lucenalodging

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable //start of reset password
fun globalResetPassword(navController: NavHostController, auth: FirebaseAuth, db : FirebaseFirestore){
    var email by remember {
        mutableStateOf("")
    }
    var warn by remember{
        mutableStateOf("")
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        Column {
            NotLoggedInTopBar(topBarValue = "Reset Account Password")
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
                                BackImage(navController = navController, backTo ="LoginAs" )
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
                                    text = "Please fill out information",
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
                                    Text(text = "email",
                                        fontSize = 17.sp,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                    TextField(
                                        value = email,
                                        onValueChange = {email = it},
                                        colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                            unfocusedContainerColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(10.dp),
                                        label = { Text("Email", color = Color.Gray) },
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Column (modifier = Modifier
                                        .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally){
                                        Text(text = "$warn", color = Color.Red)
                                    }
                                }
                                Spacer(modifier = Modifier.height(50.dp))
                                Column (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Button(
                                        onClick = {
                                            auth.sendPasswordResetEmail(email)
                                                .addOnSuccessListener {
                                                    warn = "Reset Email sent to $email"
                                                }
                                                .addOnFailureListener {
                                                    warn = "Invalid Email"
                                                }
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
                                            text = "Send to Email",
                                            color = Color.Black,
                                            fontSize = 12.sp,
                                            letterSpacing = 2.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(text = "Already Have an Account?")
                                    Text(text = "Login",
                                        modifier = Modifier
                                            .clickable(
                                                onClick = {
                                                    navController.navigate("LoginAs")
                                                }
                                            ),
                                        textDecoration = TextDecoration.Underline,
                                        color = Color.Blue)

                                }
                            }
                            MainSpacer()
                        }
                    }
                }
            }
        }
    }
}