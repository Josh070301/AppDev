package com.example.lucenalodging

import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.util.UUID

@Composable
fun LandOwnerProfile(navController: NavHostController, auth :FirebaseAuth, db : FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember{
        mutableStateOf("")
    }
    var email by remember{
        mutableStateOf("")
    }
    var totalPost by remember{
        mutableStateOf(0)
    }
    var profilePic by remember {
        mutableStateOf("")
    }
    var hasProfile by remember{
        mutableStateOf(false)
    }
    if (uid != null){
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    fullName = document.getString("fullName").toString()
                    email = document.getString("email").toString()
                    if (document.getString("UserProfile") != null){
                        profilePic = document.getString("UserProfile").toString()
                        hasProfile = true
                    }
                    else {
                        hasProfile = false
                    }
                }
            }
        db.collection("LandLordPost").whereEqualTo("uid", uid)
            .get()
            .addOnSuccessListener { doc ->
                var x =0
                for (count in doc){
                    x++
                    totalPost = x //counts the total number of posts created by this landlord
                }
            }
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,fullName, usage = "User Profile", userType = "LandOwner")//scaffold on ScaffoldAndEtc.kt
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
                            text = "Land Owner",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )
                        if (hasProfile) {
                            val ref: StorageReference =
                                FirebaseStorage.getInstance().getReference(profilePic)
                            var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) } //storage of image

                            LaunchedEffect(profilePic) {
                                val ONE_MEGABYTE: Long = 1024 * 1024
                                try {
                                    val bytes = ref.getBytes(ONE_MEGABYTE).await()
                                    val bitmap = BitmapFactory.decodeByteArray(
                                        bytes,
                                        0,
                                        bytes.size
                                    ) // turn to image bits
                                    imageBitmap = bitmap.asImageBitmap()
                                } catch (e: Exception) {
                                    // Handle any errors
                                }
                            }

                            imageBitmap?.let { img ->
                                Image(
                                    bitmap = img,
                                    contentDescription = "Images",
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(150.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }
                        else {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                                contentDescription = "Profile picture",
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(150.dp)
                                    .clip(CircleShape)

                            )
                        }
                        Row (
                            modifier = Modifier
                                .padding(start = 40.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "$fullName",
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
                                    .clickable(
                                        onClick = {
                                            navController.navigate("LandOwnerEditUserProfile")
                                        }
                                    )
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
                            Text(text = "$email")
                        }
                        Row (
                            modifier = Modifier
                                .height(50.dp)
                        ){
                            Text(text = "Total Post: ",fontWeight = FontWeight.Bold)
                            Text(text = "$totalPost")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LandOwnerEditUserProfile(navController: NavHostController, auth :FirebaseAuth, db : FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember {
        mutableStateOf("")
    }
    var fullNameMaxChar = 40
    var email by remember {
        mutableStateOf("")
    }
    var totalPost by remember {
        mutableStateOf(0)
    }

    var hasProfile by remember {
        mutableStateOf(false)
    }
    var currentProfile by remember {
        mutableStateOf("")
    }
    if (uid != null) {
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    fullName = document.getString("fullName").toString()
                    email = document.getString("email").toString()
                    if (document.getString("UserProfile") == null) {
                        hasProfile = false
                    } else {
                        hasProfile = true
                        currentProfile = document.getString("UserProfile").toString()
                    }
                }
            }

        db.collection("LandLordPost").whereEqualTo("uid", uid)
            .get()
            .addOnSuccessListener { doc ->
                if (doc != null) {
                    var x = 0
                    for (count in doc) {//counts the total number of posts created by this landlord if found a doc
                        x++
                        totalPost = x
                    }
                }
            }
    }
    var changingProfile by remember {
        mutableStateOf(false)
    }
    var newProfile by remember { //app local storage of selected images
        mutableStateOf<Uri?>(null) //list
    }
    if (newProfile != null) {
        changingProfile = true
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            newProfile = uri
        }
    )
    var imagePath by remember {
        mutableStateOf("")
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,fullName, usage = "User Profile", userType = "LandOwner")//scaffold on ScaffoldAndEtc.kt
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
                        .height(400.dp),
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
                            text = "Land Owner",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )
                        if (hasProfile) {
                            if (changingProfile == true) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = newProfile),
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(150.dp)
                                        .clip(CircleShape)
                                        .clickable(
                                            onClick = {
                                                galleryLauncher.launch("image/*")
                                            },
                                        )
                                )
                            }
                            else {
                                val ref: StorageReference =
                                    FirebaseStorage.getInstance().getReference(currentProfile)
                                var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) } //storage of image

                                LaunchedEffect(currentProfile) {
                                    val ONE_MEGABYTE: Long = 1024 * 1024
                                    try {
                                        val bytes = ref.getBytes(ONE_MEGABYTE).await()
                                        val bitmap = BitmapFactory.decodeByteArray(
                                            bytes,
                                            0,
                                            bytes.size
                                        ) // turn to image bits
                                        imageBitmap = bitmap.asImageBitmap()
                                    } catch (e: Exception) {
                                        // Handle any errors
                                    }
                                }

                                imageBitmap?.let { img ->
                                    Image(
                                        bitmap = img,
                                        contentDescription = "Images",
                                        modifier = Modifier
                                            .height(150.dp)
                                            .width(150.dp)
                                            .clip(CircleShape)
                                            .clickable(
                                                onClick = {
                                                    galleryLauncher.launch("image/*")
                                                },
                                            )
                                    )
                                }
                            }
                        }
                        else {
                            if (changingProfile == true) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = newProfile),
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(150.dp)
                                        .clip(CircleShape)
                                        .clickable(
                                            onClick = {
                                                galleryLauncher.launch("image/*")
                                            },
                                        )
                                )
                            }
                            else {
                                Image(
                                    painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(150.dp)
                                        .clip(CircleShape)
                                        .clickable(
                                            onClick = {
                                                galleryLauncher.launch("image/*")
                                            },
                                        )
                                )
                            }
                        }

                        OutlinedTextField( //encountered an error here where size was not initialized so it closes the app
                            value = fullName,
                            onValueChange = { if (it.length <= 40) fullName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 40.dp, end = 40.dp),
                            colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                unfocusedContainerColor = Color.White
                            ),
                            maxLines = 1, //set max lines for this textfield
                            supportingText = { //counts max chars
                                Text(
                                    text = "${fullName.length} / $fullNameMaxChar",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.End,
                                )
                            },
                        )
                        Row (
                            modifier = Modifier
                                .padding(start = 40.dp, end = 40.dp)
                                .height(50.dp)
                                .fillMaxWidth(),
                        ){
                            Button(onClick = {
                                if (uid != null) {
                                    val ref: StorageReference =
                                        FirebaseStorage.getInstance().getReference()
                                    val path = "imagesfor$email/UserProfile.jpg"
                                    val uploadHere = ref.child(path)
                                    if (newProfile != null) { //uploads picture in path fire storage
                                        uploadHere.putFile(newProfile!!)
                                    }
                                    imagePath = path //used for update in firestore

                                    db.collection("Users").document(uid).update(
                                        mapOf(
                                            "fullName" to fullName,
                                            "UserProfile" to imagePath
                                        )
                                    ).addOnSuccessListener {
                                        navController.navigate("LandOwnerUserProfile")
                                    }

                                }
                            }) {
                                Text(text = "Save")
                            }
                            Button(onClick = {
                                navController.navigate("LandOwnerUserProfile")
                            }) {
                                Text(text = "Cancel")
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))

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
                            Text(text = "$email")
                        }
                        Row (
                            modifier = Modifier
                                .height(50.dp)
                        ){
                            Text(text = "Total Post: ",fontWeight = FontWeight.Bold)
                            Text(text = "$totalPost")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Test(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember {
        mutableStateOf("")
    }
    var fullNameMaxChar = 40
    var email by remember {
        mutableStateOf("")
    }
    var totalPost by remember {
        mutableStateOf(0)
    }

    var hasProfile by remember {
        mutableStateOf(false)
    }
    var currentProfile by remember {
        mutableStateOf("")
    }
    if (uid != null) {
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    fullName = document.getString("fullName").toString()
                    email = document.getString("email").toString()
                    if (document.getString("profilePicture") == null) {
                        hasProfile = false
                    } else {
                        hasProfile = true
                        currentProfile = document.getString("profilePicture").toString()
                    }
                }
            }

        db.collection("LandLordPost").whereEqualTo("uid", uid)
            .get()
            .addOnSuccessListener { doc ->
                if (doc != null) {
                    var x = 0
                    for (count in doc) {//counts the total number of posts created by this landlord if found a doc
                        x++
                        totalPost = x
                    }
                }
            }
    }
    var changingProfile by remember {
        mutableStateOf(false)
    }
    var newProfile by remember { //app local storage of selected images
        mutableStateOf<Uri?>(null) //list
    }
    if (newProfile != null) {
        changingProfile = true
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            newProfile = uri
        }
    )
    var imagePath by remember {
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(
            navController,
            fullName,
            usage = "User Profile",
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
                Row(
                    modifier = Modifier
                        .height(300.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Edit User Profile",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )
                        MainSpacer()// at ScaffoldAndEtc.kt
                        Text(
                            text = "Land Owner",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )
                        if (hasProfile) {
                            if (changingProfile == true) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = newProfile),
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(150.dp)
                                        .clip(CircleShape)
                                        .clickable(
                                            onClick = {
                                                galleryLauncher.launch("image/*")
                                            },
                                        )
                                )
                            } else {
                                val ref: StorageReference =
                                    FirebaseStorage.getInstance().getReference(currentProfile)
                                var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) } //storage of image

                                LaunchedEffect(currentProfile) {
                                    val ONE_MEGABYTE: Long = 1024 * 1024
                                    try {
                                        val bytes = ref.getBytes(ONE_MEGABYTE).await()
                                        val bitmap = BitmapFactory.decodeByteArray(
                                            bytes,
                                            0,
                                            bytes.size
                                        ) // turn to image bits
                                        imageBitmap = bitmap.asImageBitmap()
                                    } catch (e: Exception) {
                                        // Handle any errors
                                    }
                                }

                                imageBitmap?.let { img ->
                                    Image(
                                        bitmap = img,
                                        contentDescription = "Images",
                                        modifier = Modifier
                                            .height(150.dp)
                                            .width(150.dp)
                                            .clip(CircleShape)
                                            .clickable(
                                                onClick = {
                                                    galleryLauncher.launch("image/*")
                                                },
                                            )
                                    )
                                }
                            }
                        } else {
                            if (changingProfile == true) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = newProfile),
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(150.dp)
                                        .clip(CircleShape)
                                        .clickable(
                                            onClick = {
                                                galleryLauncher.launch("image/*")
                                            },
                                        )
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                                    contentDescription = "Profile picture",
                                    modifier = Modifier
                                        .height(150.dp)
                                        .width(150.dp)
                                        .clip(CircleShape)
                                        .clickable(
                                            onClick = {
                                                galleryLauncher.launch("image/*")
                                            },
                                        )
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .padding(start = 40.dp),
                        ) {
                            OutlinedTextField(
                                value = fullName,
                                onValueChange = { if (it.length <= 40) fullName = it },
                                modifier = Modifier
                                    .fillMaxSize(),
                                colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                    unfocusedContainerColor = Color.White
                                ),
                                maxLines = 10, //set max lines for this textfield
                                supportingText = { //counts max chars
                                    Text(
                                        text = "${fullName.length} / $fullNameMaxChar",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.End,
                                    )
                                },
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Row {
                                Button(onClick = {
                                    if (uid != null) {
                                        val ref: StorageReference =
                                            FirebaseStorage.getInstance().getReference()
                                        val path = "imagesfor$email/UserProfile.jpg"
                                        val uploadHere = ref.child(path)
                                        if (newProfile != null) { //uploads picture in path fire storage
                                            uploadHere.putFile(newProfile!!)
                                        }
                                        imagePath = path //used for update in firestore

                                        db.collection("Users").document(uid).update(
                                            mapOf(
                                                "fullName" to fullName,
                                                "UserProfile" to imagePath
                                            )
                                        ).addOnSuccessListener {
                                            navController.navigate("LandOwnerUserProfile")
                                        }

                                    }
                                }) {
                                    Text(text = "Save")
                                }
                                Button(onClick = {
                                    navController.navigate("LandOwnerUserProfile")
                                }) {
                                    Text(text = "Cancel")
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            MainSpacer()// at ScaffoldAndEtc.kt
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 30.dp, top = 20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .height(30.dp)
                            ) {
                                Text(
                                    text = "Email: ",
                                    fontWeight = FontWeight.Bold
                                ) //soon parameters arguments
                                Text(text = "$email")
                            }
                            Row(
                                modifier = Modifier
                                    .height(50.dp)
                            ) {
                                Text(text = "Total Post: ", fontWeight = FontWeight.Bold)
                                Text(text = "$totalPost.size")
                            }
                        }
                    }
                }
            }
        }
    }
}

