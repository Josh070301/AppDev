package com.example.lucenalodging

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginAs(navController : NavHostController){
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

            ){

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
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, bottom = 40.dp, end = 20.dp)

            ){
                Column(
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
                        Row(
                            modifier = Modifier
                                .padding(start = 60.dp, top = 40.dp) // specific paddings
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .padding(top = 10.dp),

                        ) {
                        Column (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Button(
                                onClick = {
                                          navController.navigate("LoginAsTenant")
                                },
                                modifier = Modifier
                                    .size(width = 200.dp, height = 45.dp)
                                    .border(1.dp, Color.Black, RoundedCornerShape(13.dp)),
                                shape = RoundedCornerShape(13.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(color = 0xFFF2B398),
                                ),
                            ) {
                                Text(text = "Login as Tenant", color = Color.Black)
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(
                                onClick = {
                                    navController.navigate("LoginAsLandOwner")
                                          },
                                modifier = Modifier
                                    .size(width = 200.dp, height = 45.dp)
                                    .border(1.dp, Color.Black, RoundedCornerShape(13.dp)),
                                shape = RoundedCornerShape(13.dp), //changes default value of button shape
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(color = 0xFFF2B398),
                                ),
                            ) {
                                Text(text = "Login as Land Owner", color = Color.Black)
                            }
                        }
                    }
                }
            }

        }
    }
}

