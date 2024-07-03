package com.example.lucenalodging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
        Column (modifier = Modifier
            .fillMaxSize()
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(bottomStart = 10.dp)) // 1 side of the border
                    .clip(RoundedCornerShape(bottomEnd = 10.dp))
                    .background(Color(color = 0xFFC2997C)),// color a column or row using background
            ){
                Row (
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(text = "Hello, Land Owner $userName")
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Your Posts", fontWeight = FontWeight.Bold)
            }
            Row (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .height(650.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(Color(color = 0xFFF8E4BF))
                ) {
                    Text(text = "TEST1")
                }
            }
            Spacer(modifier = Modifier.height(5.dp))//start of footer
            FootMenu()
        }
    }
}
@Composable //footer menu to be called for every UI logged in
fun FootMenu(){
    Row (modifier = Modifier
        .background(Color(color = 0xFFF8E4BF))
        .fillMaxSize(),
    ){
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WelcomeLandOwner(navController = rememberNavController(), userName ="Joshua")
}