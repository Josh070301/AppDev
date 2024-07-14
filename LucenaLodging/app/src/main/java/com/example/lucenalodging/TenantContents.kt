package com.example.lucenalodging

import android.graphics.BitmapFactory
import android.net.Uri
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

//codes of tenant UI and functionalities are here unlike land owner which are separated
@Composable
fun WelcomeTenant(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember {
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
    var maxMorePost by remember {
        mutableStateOf(5)
    }
    var currentPostCount by remember {
        mutableStateOf(0)
    }

    if (uid != null) {
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    fullName = document.getString("fullName").toString()
                }
            }
        db.collection("LandLordPost")
            .get()
            .addOnSuccessListener { doc ->
                posts.clear() // clears the current list to avoid duplication
                if (doc != null){
                    for (document in doc){
                        val documentId = document.id
                        val uid = document.getString("uid") ?:""
                        val email = document.getString("email") ?: ""
                        val selectRoomTitle = document.getString("roomTitle")?: ""
                        val location  = document.getString("location")?: ""
                        val curfew  = document.getString("curfew")?: ""
                        val roomIncludes  = document.getString("roomIncludes")?: ""
                        val peopleCount  = document.getLong("peopleCount") ?.toInt() ?: 0
                        val oneMonthAdvance  = document.getBoolean("oneMonthAdvance") ?: false
                        val oneMonthDeposit  = document.getBoolean("oneMonthDeposit") ?: false
                        val anyID  = document.getBoolean("anyID") ?: false
                        val available  = document.getBoolean("available") ?:false
                        val price  = document.getString("price") ?: ""
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
                    }
                }
                else{
                    warn = "No Posts Yet"
                }

            }
            .addOnFailureListener { e->
                warn = "Error getting data"
            }

    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ){
        BottomMenu(navController,fullName, usage ="Browse Post", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                    Spacer(modifier = Modifier.height(20.dp))
                    for (data in posts) {
                        if(currentPostCount >= maxMorePost){
                            break
                        }
                        else{
                            userContent(
                                navController, fullName, userType = "Tenants",
                                documentID = data.documentId,
                                anyID = data.anyID,
                                available = data.available,
                                curfew = data.curfew,
                                responseImages = data.selectImages,
                                location = data.location,
                                oneMonthAdvance = data.oneMonthAdvance,
                                oneMonthDeposit = data.oneMonthDeposit,
                                peopleCount = data.peopleCount,
                                price = data.price,
                                roomIncludes = data.roomIncludes,
                                selectRoomTitle = data.selectRoomTitle,
                                uid = data.uid,
                                email = data.email,
                                userProfile = data.userProfile,
                                posterName = data.fullName
                            ) //calls user Content can be multiple dependin on count
                        }
                    }
                    /*Column (modifier = Modifier
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "More",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 10.dp)
                                .clickable(
                                    onClick = {maxMorePost+=5}
                                ),
                        )
                    }*/
                }
            }
        }
    }
}
@Composable
fun TenantSingleMessages(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember {
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
    }
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
            fullName,
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
                        BackImage(navController = navController, backTo = "TenantMessages")
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
fun TenantMessages(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember {
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
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,fullName, usage = "Messages", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                            SearchBar(navController, fullName, userType = "Tenant") //in ScaffoldAndEtc.kt
                        }
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ){
                        repeat(10){
                            messagesContent(navController, fullName, userType = "Tenants")//in ScaffoldAndEtc.kt
                        }
                    }
                }
            }
        }
    }
}

@Composable //aboutPost now
fun TenantBrowseMore(navController : NavHostController, auth: FirebaseAuth, db: FirebaseFirestore, documentID : String){
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
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ){
        BottomMenu(navController,fullName, usage ="Browse Post", userType = "Tenants")
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
                            BackImage(navController = navController, backTo = "TenantBrowsePost")
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
                                .height(80.dp)
                                .fillMaxWidth()
                                .padding(start = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
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
                            Row (
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
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
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(60.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ){
                                        OutlinedButton(
                                            onClick = {
                                                navController.navigate("TenantSingleMessages")
                                            },
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
fun SearchPost(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember {
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
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,fullName, usage = "Search", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                            SearchBar(navController, fullName, userType = "Tenants") //in ScaffoldAndEtc.kt
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
//@Composable
/*fun TenantProfile(navController: NavHostController, auth : FirebaseAuth, db : FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember {
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
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,fullName, usage = "User Profile", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
*/
@Composable
fun TenantProfile(navController: NavHostController, auth :FirebaseAuth, db : FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember{
        mutableStateOf("")
    }
    var email by remember{
        mutableStateOf("")
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
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,fullName, usage = "User Profile", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                                            navController.navigate("TenantEditUserProfile")
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
                    }
                }
            }
        }
    }
}

@Composable
fun TenantEditUserProfile(navController: NavHostController, auth :FirebaseAuth, db : FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember {
        mutableStateOf("")
    }
    var fullNameMaxChar = 40
    var email by remember {
        mutableStateOf("")
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
        BottomMenu(navController,fullName, usage = "User Profile", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                                    if(newProfile != null){
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
                                            navController.navigate("TenantUserProfile")
                                        }
                                    }
                                    else {
                                        db.collection("Users").document(uid).update(
                                            mapOf(
                                                "fullName" to fullName,
                                            )
                                        ).addOnSuccessListener {
                                            navController.navigate("TenantUserProfile")
                                        }
                                    }
                                }
                            }) {
                                Text(text = "Save")
                            }
                            Button(onClick = {
                                navController.navigate("TenantUserProfile")
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
                    }
                }
            }
        }
    }
}


@Composable
fun TenantSettings(navController: NavHostController, auth: FirebaseAuth, db : FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember{
        mutableStateOf("")
    }
    if (uid != null){
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    val FullNameFromDB = document.getString("fullName")
                    fullName = FullNameFromDB.toString()
                }
            }
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController,fullName, usage = "Settings", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                                        navController.navigate("TenantChangePassword")
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
                                        auth.signOut() //signs out current logged in user
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
fun TenantChangePassword(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember{
        mutableStateOf("")
    }
    if (uid != null){
        db.collection("LandLords").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    val FullNameFromDB = document.getString("fullName")
                    fullName = FullNameFromDB.toString()
                }
            }
    }
    var oldPassword by remember {
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
        BottomMenu(navController,fullName, usage = "Settings", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                            BackImage(navController = navController, backTo ="TenantSettings" )
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
                            Spacer(modifier = Modifier.height(20.dp))
                            Column (modifier = Modifier
                                .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally){
                                Text(text = "$warn", color = Color.Red)
                            }
                            Spacer(modifier = Modifier.height(50.dp))
                            Button(
                                onClick = {
                                    val user = auth.currentUser
                                    if (user != null && oldPassword.isNotEmpty()){
                                        val currentCredential = EmailAuthProvider.getCredential(user.email!!, oldPassword) // checks in firebase if there is the current logged in user email and if password is same
                                        user.reauthenticate(currentCredential) //if reauthenticate succeeds
                                            .addOnSuccessListener {
                                                navController.navigate("TenantConfirmedPassword")
                                            }
                                            .addOnFailureListener {
                                                warn = "Password Does Not Match the current Password"
                                            }
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
                        MainSpacer()
                    }
                }
            }
        }
    }
}

@Composable
fun TenantConfirmedPassword(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember{
        mutableStateOf("")
    }
    if (uid != null){
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    val FullNameFromDB = document.getString("fullName")
                    fullName = FullNameFromDB.toString()
                }
            }
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var confirmedPassword by remember {
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
        BottomMenu(navController,fullName, usage = "Settings", userType = "Tenants")//scaffold on ScaffoldAndEtc.kt
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
                            BackImage(navController = navController, backTo ="TenantSettings" )
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
                            Spacer(modifier = Modifier.height(20.dp))
                            Column (modifier = Modifier
                                .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally){
                                Text(text = "$warn", color = Color.Red)
                            }
                            Spacer(modifier = Modifier.height(50.dp))
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Button(
                                    onClick = {
                                        if (newPassword.length > 7 && confirmedPassword == newPassword){ //conditions if password is equal for update
                                            val user = auth.currentUser
                                            if(user != null){
                                                user.updatePassword(newPassword) //updates password in firebase
                                                navController.navigate("TenantChangedPasswordSuccess")
                                            }
                                        }
                                        else if(newPassword.length < 8){
                                            warn = "Please enter minimum of 8 characters"
                                        }
                                        else if(newPassword != confirmedPassword){
                                            warn = "Passwords Missmatch"
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
@Composable
fun TenantChangedPasswordSuccess(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember{
        mutableStateOf("")
    }
    if (uid != null){
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    val FullNameFromDB = document.getString("fullName")
                    fullName = FullNameFromDB.toString()
                }
            }
    }
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ) {
        BottomMenu(navController, fullName, usage = "Settings", userType = "Tenants")
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
                                    navController.navigate("TenantSettings")
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
