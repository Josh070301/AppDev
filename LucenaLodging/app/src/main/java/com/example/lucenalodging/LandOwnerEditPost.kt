package com.example.lucenalodging

import android.graphics.BitmapFactory
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

@Composable //aboutPost now
fun LandOwnerEditPost(navController : NavHostController, auth: FirebaseAuth, db :FirebaseFirestore, documentID : String){
    val uid = auth.currentUser?.uid
    var fullName by remember{
        mutableStateOf("")
    }
    data class Post(
        val documentId: String = "",
        val uid : String = "",
        val email : String = "",
        val selectRoomTitle : String = "",
        val location : String= "",
        val curfew : String= "",
        val roomIncludes : String= "",
        val peopleCount : Int= 0,
        val oneMonthAdvance : Boolean= false,
        val oneMonthDeposit : Boolean= false,
        val anyID : Boolean= false,
        val available : Boolean= false,
        val price : String= "",
        val userProfile : String= "",
        val fullName : String= "",
        val selectImages : List<String> = emptyList(),
    )
    var warn by remember {
        mutableStateOf("")
    }
    var posts = remember {
        mutableListOf<Post>()
    }
    var hasUserProfile by remember{
        mutableStateOf("")
    }
    if (uid != null) {
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    fullName = document.getString("fullName").toString()
                }
            }
        db.collection("LandLordPost").document(documentID)
            .get()
            .addOnSuccessListener { document ->
                posts.clear() // clears the current list to avoid duplication
                if (document != null) {
                    val documentId = document.id
                    val uid = document.getString("uid") ?: ""
                    val email = document.getString("email") ?: ""
                    val selectRoomTitle = document.getString("roomTitle") ?: ""
                    val location = document.getString("location") ?: ""
                    val curfew = document.getString("curfew") ?: ""
                    val roomIncludes = document.getString("roomIncludes") ?: ""
                    val peopleCount = document.getLong("peopleCount")?.toInt() ?: 0
                    val oneMonthAdvance = document.getBoolean("oneMonthAdvance") ?: false
                    val oneMonthDeposit = document.getBoolean("oneMonthDeposit") ?: false
                    val anyID = document.getBoolean("anyID") ?: false
                    val available = document.getBoolean("available") ?: false
                    val price = document.getString("price") ?: ""
                    val userProfile = document.getString("UserProfile") ?:""
                    val fullName = document.getString("fullName") ?:""
                    val selectImages = document.get("images") as? List<String> ?: emptyList()


                    val storagePost = Post(
                        documentId,
                        uid,
                        email,
                        selectRoomTitle,
                        location, curfew,
                        roomIncludes,
                        peopleCount,
                        oneMonthAdvance,
                        oneMonthDeposit,
                        anyID,
                        available,
                        price,
                        userProfile,
                        fullName,
                        selectImages,
                    )
                    posts.add(storagePost)
                } else {
                    warn = "No Posts Yet"
                }
            }
    }
    var documentId by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var selectRoomTitle by remember {
        mutableStateOf("")
    }
    var location by remember {
        mutableStateOf("")
    }
    var curfew by remember {
        mutableStateOf("")
    }
    var roomIncludes by remember {
        mutableStateOf("")
    }
    var peopleCount by remember {
        mutableStateOf(1)
    }
    var oneMonthAdvance by remember {
        mutableStateOf(false)
    }
    var oneMonthDeposit by remember {
        mutableStateOf(false)
    }
    var anyID by remember {
        mutableStateOf(false)
    }
    var available by remember {
        mutableStateOf(false)
    }
    var price by remember {
        mutableStateOf("")
    }
    var posterName by remember{
        mutableStateOf("")
    }
    for (pic in posts){
        documentId = pic.documentId
        email = pic.email
        selectRoomTitle = pic.selectRoomTitle
        location = pic.location
        curfew = pic.curfew
        roomIncludes = pic.roomIncludes
        peopleCount = pic.peopleCount
        oneMonthAdvance = pic.oneMonthAdvance
        oneMonthDeposit = pic.oneMonthDeposit
        anyID = pic.anyID
        available = pic.available
        price = pic.price
        posterName = pic.fullName
        hasUserProfile = pic.userProfile
    }
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color(color = 0xFFFDF7E4)
        ) {
            BottomMenu(navController, fullName, usage = "Browse Post", userType = "LandOwner")
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
                                .height(70.dp)
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 20.dp, top = 20.dp),
                        ) {
                            Row {
                                BackImage(
                                    navController = navController,
                                    backTo = "LandOwnerBrowsePost"
                                )
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
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .height(80.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier
                                        .height(80.dp)
                                        .width(80.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    if(hasUserProfile.isNotEmpty()){
                                        val ref : StorageReference = FirebaseStorage.getInstance().getReference(hasUserProfile)
                                        var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) } //storage of image
                                        LaunchedEffect(hasUserProfile) {
                                            val ONE_MEGABYTE: Long = 1024 * 1024
                                            try {
                                                val bytes = ref.getBytes(ONE_MEGABYTE).await() //takes image location in firebase
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
                                                    .height(170.dp)
                                                    .width(170.dp)
                                                    .clip(CircleShape)
                                            )
                                        }
                                    }
                                    else{
                                        Image(painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                                            contentDescription = "User profile mini image" ,
                                            modifier = Modifier
                                                .fillMaxSize()
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "$posterName", fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
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
                            ) {
                                Row (
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    for (pic in posts){
                                        val imageDir = pic.selectImages
                                        for (x in imageDir){
                                            val ref : StorageReference = FirebaseStorage.getInstance().getReference(x)
                                            var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) } //storage of image

                                            LaunchedEffect(x) {
                                                val ONE_MEGABYTE: Long = 1024 * 1024
                                                try {
                                                    val bytes = ref.getBytes(ONE_MEGABYTE).await()
                                                    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size) // turn to image bits
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
                                                        .weight(1f)
                                                        .padding(8.dp),
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 10.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight(),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .verticalScroll(rememberScrollState())
                                    ) {
                                        Text(
                                            text = "$selectRoomTitle",
                                            fontWeight = FontWeight.Bold
                                        ) //soon to be input parameter from arguments
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(text = "$price PHP Monthly")
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(text = "Location", fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(text = "$location")//soon description input
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            text = "Curfew",
                                            fontWeight = FontWeight.Bold
                                        ) //soon to be input parameter from arguments
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(text = "$curfew")
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(
                                            text = "Room Includes | $peopleCount Persons Max",
                                            fontWeight = FontWeight.Bold
                                        ) //soon to be input parameter from arguments
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(text = "$roomIncludes")
                                        Text(
                                            text = "Room Requirements",
                                            fontWeight = FontWeight.Bold
                                        ) //soon to be input parameter from arguments
                                        Spacer(modifier = Modifier.height(3.dp))
                                        if(oneMonthAdvance == true){
                                            Text(text = "1 Month Advance")
                                        }
                                        if(oneMonthDeposit == true){
                                            Text(text = "1 Month Deposit")
                                        }
                                        if(anyID == true){
                                            Text(text = "Any ID")
                                        }
                                        if(oneMonthDeposit == false && oneMonthAdvance == false && anyID == false){
                                            Text(text = "None")
                                        }
                                        Text(
                                            text = "Availability",
                                            fontWeight = FontWeight.Bold
                                        ) //soon to be input parameter from arguments
                                        Spacer(modifier = Modifier.height(3.dp))
                                        if (available == true){
                                            Text(text = "Available")
                                        }else{
                                            Text(text = "Not Available")
                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(60.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            OutlinedButton(
                                                onClick = {
                                                    navController.navigate("LandOwnerUpdate?documentID=$documentID")
                                                },//soon navigates
                                                modifier = Modifier
                                                    .width(150.dp),
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = Color(
                                                        color = 0xFFF2B398
                                                    )
                                                )
                                            ) {
                                                Text(text = "Edit", color = Color.Black)
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

