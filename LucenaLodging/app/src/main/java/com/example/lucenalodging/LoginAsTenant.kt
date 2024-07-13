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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore


//this code contains LoginAsTenant, create account as tenant, reset password as tenant

@Composable
fun LoginAsTenant(navController : NavHostController, auth: FirebaseAuth, db :FirebaseFirestore){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var warn by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        Column (

        ){
            NotLoggedInTopBar(topBarValue = "Login as Tenant")
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
                                value = email,
                                onValueChange = {email = it},
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
                                        navController.navigate("TenantReset")
                                    }
                                )
                            ,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                    Column (modifier = Modifier
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text = "$warn", color = Color.Red)
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
                                email = email.trim()
                                password = password.trim()
                                if (email != "" && password != ""){
                                    auth.signInWithEmailAndPassword(email, password)
                                        .addOnSuccessListener { //if its in firebase and successful
                                            val uid = auth.currentUser?.uid
                                            if (uid != null){
                                                db.collection("Users").document(uid)
                                                    .get()
                                                    .addOnSuccessListener { doc ->
                                                        if (doc != null){
                                                            val user = auth.currentUser
                                                            val userType = doc.getString("userType")
                                                            if (user != null){
                                                                if(user.isEmailVerified){
                                                                    if (userType == "Tenant"){ //userType in firestore
                                                                        navController.navigate("TenantBrowsePost?email=$email")
                                                                    }
                                                                    else{
                                                                        warn = "account is not a Tenant type" //returns in warning that account is not a landlord type
                                                                        auth.signOut() //signs out the auth login attempt
                                                                    }
                                                                }
                                                                else{
                                                                    user.delete()
                                                                        .addOnSuccessListener {
                                                                            warn = "Email account is not yet Verified\nPlease verify again through sign up"
                                                                            db.collection("Users").document(uid).delete()
                                                                        }
                                                                }
                                                            }
                                                        }
                                                    }
                                            }
                                        }
                                        .addOnFailureListener { //if email sign in with password is not successful
                                            warn = "Invalid Login credentials"
                                        }
                                }
                                else{
                                    warn = "Please fill all fields"
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
                        Text(text = "Don't have a Tenant Account?")
                        Text(text = "Sign up Here",
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        navController.navigate("TenantCreateAccount")
                                    }
                                ),
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

@Composable
fun TenantCreateAccount(navController: NavHostController, auth: FirebaseAuth, db : FirebaseFirestore){
    var email by remember {
        mutableStateOf("")
    }
    var fullName by remember {
        mutableStateOf("")
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var confirmedPassword by remember {
        mutableStateOf("")
    }
    var warn by remember {
        mutableStateOf("")
    }
    var passReq by remember{
        mutableStateOf("")
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        Column {
            NotLoggedInTopBar(topBarValue = "Sign Up Tenant Account")
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
                                BackImage(navController = navController, backTo ="LoginAsTenant" )
                            }
                        }
                        MainSpacer()// at ScaffoldAndEtc.kt
                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
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
                                    Text(text = "Email",
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
                                    Text(text = "$warn", color = Color.Red)
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(text = "Full Name",
                                        fontSize = 17.sp,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                    TextField(
                                        value = fullName,
                                        onValueChange = {fullName = it},
                                        colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                            unfocusedContainerColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(10.dp),
                                        label = { Text("Enter Full Name Here", color = Color.Gray) },
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(text = "Password",
                                        fontSize = 17.sp,
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
                                    Text(text = "Confirm Password",
                                        fontSize = 17.sp,
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
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(text = "$passReq", color = Color.Red)
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Column (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Button(
                                        onClick = {
                                            if (newPassword.length > 7 && newPassword == confirmedPassword) {
                                                auth.createUserWithEmailAndPassword(
                                                    email,
                                                    newPassword
                                                ) //firebase create
                                                    .addOnCompleteListener { task ->
                                                        val user = auth.currentUser
                                                        val uid = user?.uid //stores additional info in firestore
                                                        if(task.isSuccessful){
                                                            user?.sendEmailVerification()?.addOnCompleteListener { verifyEmail ->
                                                                if (verifyEmail.isSuccessful){
                                                                    passReq = "Verification Email sent to $email"
                                                                    if (uid != null) {
                                                                        val userMap = hashMapOf(
                                                                            "fullName" to fullName,
                                                                            "email" to email,
                                                                            "userType" to "Tenant"
                                                                        )
                                                                        db.collection("Users").document(uid)
                                                                            .set(userMap)
                                                                            .addOnSuccessListener {
                                                                                navController.navigate("TenantCreateAccountSuccess?email=$email")
                                                                            }
                                                                    }
                                                                }
                                                                else {
                                                                    passReq = "Please try again later"
                                                                }
                                                            }
                                                        }
                                                    }
                                                    .addOnFailureListener {
                                                        warn = "Email Already Taken or Invalid"
                                                    }
                                            } else if (newPassword.length <= 7) {
                                                passReq = "Password must be minimum of 8 characters"
                                            } else if (newPassword != confirmedPassword) {
                                                passReq = "Password and Confirm Password Missmatch"
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
                                            text = "Continue",
                                            color = Color.Black,
                                            fontSize = 20.sp,
                                            letterSpacing = 2.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(text = "Already Have an Account?")
                                    Text(text = "Login",
                                        modifier = Modifier
                                            .clickable(
                                                onClick = {
                                                    navController.navigate("LoginAsTenant")
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

@Composable
fun TenantCreateAccountSuccess(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore, email : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        Column {
            NotLoggedInTopBar(topBarValue = "Sign Up Tenant Account")
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
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Text(text = "Please Verify your Account by checking your $email",
                                    fontSize = 25.sp,
                                    textAlign = TextAlign.Center
                                )
                                Text(text = "Note: Please verify first before loggin in",
                                    fontSize = 18.sp,
                                    color = Color.Red,
                                    textAlign = TextAlign.Center
                                )
                                OutlinedButton(
                                    onClick = {
                                        navController.navigate("LoginAsTenant")
                                    },//soon navigates
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398)),
                                    modifier = Modifier
                                        .width(150.dp)
                                ) {
                                    Text(text = "Back to Login", color = Color.Black)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable //start of reset password
fun TenantReset(navController: NavHostController){
    var userName by remember {
        mutableStateOf("")
    }
    var fullName by remember {
        mutableStateOf("")
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        Column {
            NotLoggedInTopBar(topBarValue = "Reset Tenant Password")
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
                                BackImage(navController = navController, backTo ="LoginAsTenant" )
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
                                    Text(text = "Username",
                                        fontSize = 17.sp,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                    TextField(
                                        value = userName,
                                        onValueChange = {userName = it},
                                        colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                            unfocusedContainerColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(10.dp),
                                        label = { Text("Username", color = Color.Gray) },
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(text = "Full Name",
                                        fontSize = 17.sp,
                                        textAlign = TextAlign.Start,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                    TextField(
                                        value = fullName,
                                        onValueChange = {fullName = it},
                                        colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                            unfocusedContainerColor = Color.White
                                        ),
                                        shape = RoundedCornerShape(10.dp),
                                        label = { Text("Enter Full Name Here", color = Color.Gray) },
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
                                            navController.navigate("TenantResetPassword?userName=$userName&fullName=$fullName")
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
                                            text = "Continue",
                                            color = Color.Black,
                                            fontSize = 20.sp,
                                            letterSpacing = 2.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(text = "Already Have an Account?")
                                    Text(text = "Login",
                                        modifier = Modifier
                                            .clickable(
                                                onClick = {
                                                    navController.navigate("LoginAsTenant")
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
@Composable
fun TenantResetPassword(navController: NavHostController, userName : String, fullName : String){
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
        Column {
            NotLoggedInTopBar(topBarValue = "Reset Tenant Password")
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
                                BackImage(navController = navController, backTo ="TenantReset" )
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
                                    text = "Please Enter Account New Password",
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
                                    Text(text = "Password",
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
                                    Text(text = "Confirm Password",
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
                                            if (newPassword.length > 7 && newPassword == confirmedPassword){
                                                navController.navigate("TenantResetSuccess?userName=$userName&fullName=$fullName&password=$newPassword")
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
}
@Composable
fun TenantResetSuccess(navController: NavHostController, userName : String, fullName : String, password : String){
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        Column {
            NotLoggedInTopBar(topBarValue = "Reset Tenant Account")
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
                                .fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Text(text = "Tenant Account Reset Success",
                                    fontSize = 40.sp,
                                    textAlign = TextAlign.Center
                                )
                                OutlinedButton(
                                    onClick = {
                                        navController.navigate("LoginAsTenant")
                                    },//soon navigates
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398)),
                                    modifier = Modifier
                                        .width(150.dp)
                                ) {
                                    Text(text = "Login", color = Color.Black)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


