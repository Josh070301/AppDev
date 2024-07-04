package com.example.lucenalodging

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lucenalodging.ui.theme.LucenaLodgingTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import androidx.navigation.NavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigator()
        }
    }
}
@Composable
fun WelcomeLandOwner(navController: NavHostController, userName : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ){
        BottomMenu(userName, usage ="Browse Post")//scaffold
        Row ( // Column for the surface
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, bottom = 120.dp, end = 5.dp, top = 70.dp) //padding in top and bottom bar

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
                    userContent() //calls user Content can be multiple dependin on count
                    userContent()
                    userContent()
                    userContent()
                }
            }
        }
    }
}
@Composable
fun userContent(){
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
                onClick = { /*TODO*/ },//soon navigates
                colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398))
                ) {
                Text(text = "More Information", color = Color.Black)
            }
        }
    }
}
@Composable //scaffold menu bottom and top bar
fun BottomMenu(userName : String, usage:String){
    if (usage == "Browse Post"){
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
    else if (usage == "Messages"){
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
    else if (usage == "Post"){
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
    else if (usage == "User Profile"){
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
    else if (usage == "Settings"){
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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
                                .fillMaxWidth(),
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WelcomeLandOwner(navController = rememberNavController(), userName ="Joshua")
}