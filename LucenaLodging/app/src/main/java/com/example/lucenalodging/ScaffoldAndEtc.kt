package com.example.lucenalodging

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun BackImage(navController: NavHostController, backTo:String){
    Image(painter = painterResource(id = R.drawable.icons8_back_64), //back onClick
        contentDescription = "Back",
        modifier = Modifier
            .height(40.dp)
            .width(40.dp)
            .clickable( //added clickable modifier to navigate as back
                onClick = {
                    navController.navigate("$backTo")//parameters on where to back
                })
    )
}
@Composable
fun SearchBar(navController: NavHostController, userName: String, userType: String){
    var searchInput by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .height(50.dp)
            .width(280.dp)
            .padding(end = 10.dp),
        colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
            unfocusedContainerColor = Color.White
        ),
        shape = RoundedCornerShape(30.dp),
        value = searchInput,
        onValueChange = {searchInput = it},
        placeholder = {Text(text = "Search Names", fontSize = 14.sp)},
        leadingIcon = { Image(painter = painterResource(id = R.drawable.icons8_search_64),
            contentDescription ="Search",
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
        ) },
    )
}

@Composable
fun MainSpacer(){
    Spacer(
        modifier = Modifier
            .padding(top = 2.dp)
            .height(1.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Gray)
    )
}

@Composable
fun messagesContent(navController: NavHostController, userName : String){ //soon uses parameters for api calls
    Column(
        modifier = Modifier
            .clickable(
                onClick = {
                    navController.navigate("LandOwnerSingleMessages?userName=$userName")
                }
            )
            .height(70.dp)
            .padding(start = 11.dp, end = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
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
                Text(text = "Messaged You...", fontSize = 12.sp, modifier = Modifier.padding(start = 2.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.Bottom
                ){
                    Text(text = "2:00PM", fontSize = 10.sp)
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun userContent(navController: NavHostController,userName : String, userType : String){
    if(userType == "LandOwner"){
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .padding(start = 10.dp, end = 10.dp)
                .background(color = Color(color = 0xFFFFE5B4))
                .border(
                    1.dp,
                    Color.Gray,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Row (
                modifier = Modifier
                    .height(60.dp)
                    .padding(top = 10.dp, start = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Column (
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                        contentDescription = "User profile mini image" ,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Joshua Laude",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column ( // images needs parameter soon
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(color = 0xFFEBE7E1))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Images")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ){
                Row (
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.Top
                ){
                    Column(
                        modifier = Modifier
                    ) {
                        Text(text = "Location", fontWeight = FontWeight.Bold)
                        Text(text = "Purok Happy Valley Enverga Compound Brgy. Ibabang Dupay Lucena City")//soon description input
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Row (
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.Top
                ){
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "Room for Rent", fontWeight = FontWeight.Bold) //soon to be input parameter from arguments
                        Text(text = "3500PHP Monthly", fontWeight = FontWeight.Bold)
                    }
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                OutlinedButton(
                    onClick = {
                              navController.navigate("LandOwnerEditPost?userName=$userName")
                    },//soon navigates
                    colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398))
                ) {
                    Text(text = "More Information", color = Color.Black)
                }
            }
        }
    }
}
@Composable //scaffold menu bottom and top bar
fun BottomMenu(navController: NavHostController,userName : String, usage:String, userType: String){
    if (usage == "Browse Post" && userType == "LandOwner"){
        Scaffold(
            topBar = {
                Column (modifier = Modifier
                    .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(bottomStart = 10.dp)) // 1 side of the border
                            .clip(RoundedCornerShape(bottomEnd = 10.dp))
                            .background(Color(color = 0xFFC2997C)), // color a column or row using background
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "Hello, Land Owner $userName")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color(color = 0xFFF8E4BF)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(color = Color(color = 0xFFE7CEBC))
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerBrowsePost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.browse_post),
                                contentDescription = "Browse",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Browse Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerMessages?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_messages_250),
                                contentDescription = "Messages",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Messages", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerPost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_create_100),
                                contentDescription = "Create",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerUserProfile?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_admin_settings_male_64),
                                contentDescription = "User Profile",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "User Profile", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerSettings?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_settings_250),
                                contentDescription = "Settings",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Settings", fontSize = 11.sp)
                        }
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

            }
        }
    } //conditions because Tenant scaffold will be here too
    else if (usage == "Messages" && userType == "LandOwner"){
        Scaffold(
            topBar = {
                Column (modifier = Modifier
                    .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(bottomStart = 10.dp)) // 1 side of the border
                            .clip(RoundedCornerShape(bottomEnd = 10.dp))
                            .background(Color(color = 0xFFC2997C)), // color a column or row using background
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "Hello, Land Owner $userName")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color(color = 0xFFF8E4BF)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerBrowsePost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.browse_post),
                                contentDescription = "Browse",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Browse Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(color = Color(color = 0xFFE7CEBC))
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerMessages?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_messages_250),
                                contentDescription = "Messages",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Messages", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerPost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_create_100),
                                contentDescription = "Create",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerUserProfile?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_admin_settings_male_64),
                                contentDescription = "User Profile",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "User Profile", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerSettings?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_settings_250),
                                contentDescription = "Settings",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Settings", fontSize = 11.sp)
                        }
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

            }
        }
    }
    else if (usage == "Post" && userType == "LandOwner"){
        Scaffold(
            topBar = {
                Column (modifier = Modifier
                    .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(bottomStart = 10.dp)) // 1 side of the border
                            .clip(RoundedCornerShape(bottomEnd = 10.dp))
                            .background(Color(color = 0xFFC2997C)), // color a column or row using background
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "Hello, Land Owner $userName")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color(color = 0xFFF8E4BF)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerBrowsePost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.browse_post),
                                contentDescription = "Browse",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Browse Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerMessages?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_messages_250),
                                contentDescription = "Messages",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Messages", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(color = Color(color = 0xFFE7CEBC))
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerPost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_create_100),
                                contentDescription = "Create",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerUserProfile?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_admin_settings_male_64),
                                contentDescription = "User Profile",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "User Profile", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerSettings?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_settings_250),
                                contentDescription = "Settings",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Settings", fontSize = 11.sp)
                        }
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

            }
        }
    }
    else if (usage == "User Profile" && userType == "LandOwner"){
        Scaffold(
            topBar = {
                Column (modifier = Modifier
                    .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(bottomStart = 10.dp)) // 1 side of the border
                            .clip(RoundedCornerShape(bottomEnd = 10.dp))
                            .background(Color(color = 0xFFC2997C)), // color a column or row using background
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "Hello, Land Owner $userName")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color(color = 0xFFF8E4BF)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerBrowsePost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.browse_post),
                                contentDescription = "Browse",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Browse Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerMessages?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_messages_250),
                                contentDescription = "Messages",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Messages", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerPost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_create_100),
                                contentDescription = "Create",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(color = Color(color = 0xFFE7CEBC))
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerUserProfile?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_admin_settings_male_64),
                                contentDescription = "User Profile",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "User Profile", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerSettings?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_settings_250),
                                contentDescription = "Settings",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Settings", fontSize = 11.sp)
                        }
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

            }
        }
    }
    else if (usage == "Settings" && userType == "LandOwner"){
        Scaffold(
            topBar = {
                Column (modifier = Modifier
                    .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(bottomStart = 10.dp)) // 1 side of the border
                            .clip(RoundedCornerShape(bottomEnd = 10.dp))
                            .background(Color(color = 0xFFC2997C)), // color a column or row using background
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "Hello, Land Owner $userName")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Color(color = 0xFFF8E4BF)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerBrowsePost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.browse_post),
                                contentDescription = "Browse",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Browse Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerMessages?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_messages_250),
                                contentDescription = "Messages",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Messages", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerPost?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_create_100),
                                contentDescription = "Create",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Post", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerUserProfile?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_admin_settings_male_64),
                                contentDescription = "User Profile",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "User Profile", fontSize = 11.sp)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(color = Color(color = 0xFFE7CEBC))
                            .fillMaxHeight(), //responsive
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        navController.navigate("LandOwnerSettings?userName=$userName")
                                    }
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_settings_250),
                                contentDescription = "Settings",
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(35.dp)
                            )
                            Text(text = "Settings", fontSize = 11.sp)
                        }
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

            }
        }
    }
}