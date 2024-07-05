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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LandOwnerSingleMessages(navController: NavHostController, userName : String){
    var chatInput by remember {
        mutableStateOf("")
    }
    var chatInputMaxChar = 180
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(
            navController,
            userName,
            usage = "Messages",
            userType = "LandOwner"
        )//scaffold on ScaffoldAndEtc.kt
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
                Column (
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp)
                ){
                    Row (
                        modifier = Modifier
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        BackImage(navController = navController, backTo = "LandOwnerMessages?userName=$userName")
                        Spacer(modifier = Modifier.width(20.dp))
                        Image(painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                            contentDescription ="Profile Picture",
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(70.dp))
                        Column (
                            modifier = Modifier
                                .width(230.dp)
                        ){
                            Text(text = "Tenant Name", fontWeight = FontWeight.Bold)
                        }
                    }
                    MainSpacer()
                    Row (
                        modifier = Modifier
                            .weight(8f)
                            .padding(bottom = 10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ){
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                        ){
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                                    contentDescription ="Profile Picture",
                                    modifier = Modifier
                                        .width(70.dp)
                                        .fillMaxHeight()
                                )
                                Row (
                                    modifier = Modifier
                                        .width(150.dp)
                                        .fillMaxHeight()
                                        .background(
                                            color = Color(color = 0xFFE7B898),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .padding(start = 15.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                        Text(text = "Hello",)
                                        Column (
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalAlignment = Alignment.End
                                        ){
                                            Row (
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                                    .padding(bottom = 5.dp, end = 5.dp),
                                                verticalAlignment = Alignment.Bottom
                                            ){
                                                Text(text = "2:00PM", fontSize = 10.sp)
                                            }
                                        }
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp),
                            ){
                                Row (
                                    modifier = Modifier
                                        .fillMaxHeight(),
                                ){
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 20.dp)
                                            .padding(start = 15.dp),
                                        horizontalAlignment = Alignment.End
                                    ){
                                        Row (
                                            modifier = Modifier
                                                .width(150.dp)
                                                .fillMaxHeight()
                                                .background(
                                                    color = Color(color = 0xFFE7B898),
                                                    shape = RoundedCornerShape(10.dp)
                                                )
                                                .padding(start = 15.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ){
                                            Text(text = "Hello",)
                                            Column (
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                horizontalAlignment = Alignment.End
                                            ){
                                                Row (
                                                    modifier = Modifier
                                                        .fillMaxHeight()
                                                        .padding(bottom = 5.dp, end = 5.dp),
                                                    verticalAlignment = Alignment.Bottom
                                                ){
                                                    Text(text = "2:01PM", fontSize = 10.sp)
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                    Row (
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ){
                        Row {
                            Row (
                                modifier = Modifier
                                    .weight(6f)
                            ){
                                OutlinedTextField(
                                    value = chatInput,
                                    onValueChange = {
                                        if (it.length <= chatInputMaxChar) chatInput = it
                                    },
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    shape = RoundedCornerShape(50.dp),
                                    colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                        unfocusedContainerColor = Color.White
                                    ),
                                    maxLines = 10, //set max lines for this textfield
                                    placeholder = {Text(text = "Type Here...")},
                                    supportingText = {
                                        Text(
                                            text = "${chatInput.length} / $chatInputMaxChar",
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.End,
                                        )
                                    },
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 10.dp, bottom = 10.dp)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically

                            ){
                                Image(painter = painterResource(id = R.drawable.icons8_send_96),
                                    contentDescription = "Send Icon")
                            }
                        }
                    }
                }
            }
        }
    }
}
