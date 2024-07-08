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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

//codes of tenant UI and functionalities are here unlike land owner which are separated
@Composable
fun WelcomeTenant(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ){
        BottomMenu(navController,userName, usage ="Browse Post", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                        text = "Recommended For You",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    repeat(10){
                        Spacer(modifier = Modifier.height(20.dp))
                        userContent(navController,userName, email = "", userType = "Tenants") //calls user Content can be multiple dependin on count
                    }
                }
            }
        }
    }
}
@Composable
fun TenantSingleMessages(navController: NavHostController, userName : String){
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
            userType = "Tenants"
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
                        BackImage(navController = navController, backTo = "TenantMessages?userName=$userName")
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
                            Text(text = "Land Owner Name", fontWeight = FontWeight.Bold)
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
                            Spacer(modifier = Modifier.height(20.dp))
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


@Composable
fun TenantMessages(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,userName, usage = "Messages", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                                text = "Messages",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp),
                            )
                            MainSpacer()// at ScaffoldAndEtc.kt
                        }
                    }
                    Column (
                        modifier = Modifier
                            .height(70.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row (
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            SearchBar(navController, userName, userType = "Tenant") //in ScaffoldAndEtc.kt
                        }
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ){
                        repeat(10){
                            messagesContent(navController, userName, userType = "Tenants")//in ScaffoldAndEtc.kt
                        }
                    }
                }
            }
        }
    }
}

@Composable //aboutPost now
fun TenantBrowseMore(navController : NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ){
        BottomMenu(navController,userName, usage ="Browse Post", userType = "Tenants")
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
                            BackImage(navController = navController, backTo = "TenantBrowsePost?userName=$userName" )
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
                                                navController.navigate("TenantSingleMessages?userName=$userName")
                                            },//soon navigates
                                            modifier = Modifier
                                                .width(150.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398))
                                        ) {
                                            Text(text = "Message", color = Color.Black)
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

@Composable
fun SearchPost(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,userName, usage = "Search", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                                text = "Search A Post",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(top = 10.dp),
                            )
                            MainSpacer()// at ScaffoldAndEtc.kt
                        }
                    }
                    Column (
                        modifier = Modifier
                            .height(70.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row (
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            SearchBar(navController, userName, userType = "Tenants") //in ScaffoldAndEtc.kt
                        }
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ){

                    }
                }
            }
        }
    }
}
@Composable
fun TenantProfile(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,userName, usage = "User Profile", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                            text = "Tenant",
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
                    }
                }
            }
        }
    }
}

@Composable
fun TenantSettings(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,userName, usage = "Settings", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                                        navController.navigate("TenantChangePassword?userName=$userName")
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

@Composable
fun TenantChangePassword(navController: NavHostController, userName : String){
    var oldPassword by remember {
        mutableStateOf("")
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,userName, usage = "Settings", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                            BackImage(navController = navController, backTo ="TenantSettings?userName=$userName" )
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
                                    navController.navigate("TenantConfirmedPassword?userName=$userName")
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
fun TenantConfirmedPassword(navController: NavHostController, userName : String){
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
        BottomMenu(navController,userName, usage = "Settings", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                            BackImage(navController = navController, backTo ="TenantSettings?userName=$userName" )
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
                                text = "Enter New Password",
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
                                        navController.navigate("TenantChangedPasswordSuccess?userName=$userName")
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
fun TenantChangedPasswordSuccess(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController, userName, usage = "Settings", userType = "Tenants")
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
                                    navController.navigate("TenantSettings?userName=$userName")
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

@Preview
@Composable
fun PreviewThis(){
    TenantChangedPasswordSuccess(navController = rememberNavController(), userName = "Joshua")
}