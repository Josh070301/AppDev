package com.example.lucenalodging

import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.widget.Space
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Date
import java.util.Locale

@Composable
fun LandOwnerSingleMessages(navController: NavHostController, auth : FirebaseAuth, db : FirebaseFirestore, tenantUID : String){
    data class Message(
        val sender: String = "",
        val recipient: String = "",
        val message: String = "",
        val timestamp: String = ""
    )
    val uid = auth.currentUser?.uid
    var fullName by remember {
        mutableStateOf("")
    }
    var warn by remember{
        mutableStateOf("")
    }
    var tenantProfile by remember{
        mutableStateOf("")
    }
    var tenantFullName by remember{
        mutableStateOf("")
    }
    var totalMessages = remember {
        mutableStateListOf<Message>() //data class
    }
    if (uid != null) {
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    fullName = document.getString("fullName").toString()
                }
            }
        db.collection("Users").document(tenantUID)//gets the poster ID/LandLord ID
            .get()
            .addOnSuccessListener { doc ->
                if (doc != null) {
                    tenantProfile = doc.getString("UserProfile") ?: ""
                    tenantFullName = doc.getString("fullName") ?: ""
                }
                else{
                    warn = "Start Messaging"
                }

            }
            .addOnFailureListener { e->
                warn = "Error getting data"
            }
    }

    if (tenantUID.isNotEmpty()) {
        db.collection("Messages").document(tenantUID + uid)
            .collection("chats").orderBy("timestamp").get()
            .addOnSuccessListener { doc ->
                totalMessages.clear()
                for (document in doc) {
                    val sender = document.getString("sender") ?: ""
                    val recipient = document.getString("recipient") ?: ""
                    val message = document.getString("message") ?: ""
                    val timestamp = document.getLong("timestamp") ?:0
                    val timeStampToDate = Date(timestamp)
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val timeStampString = sdf.format(timeStampToDate)

                    val storagePost = Message(sender, recipient, message, timeStampString)
                    totalMessages.add(storagePost)
                    //Log.e("Total Messages", "Sender : $sender \nrecipient : $recipient \nmessage : $message")
                }
            }
            .addOnFailureListener { e ->
                warn = "Error getting data: ${e.message}"
                Log.e("FirebaseError", "Error getting data", e)
            }
    } else {
        warn = "Invalid LandLordUID"
    }
    var chatInput by remember {
        mutableStateOf("")
    }
    var chatInputMaxChar = 180
    val scrollState = rememberScrollState() //used for scrolling at end
    val coroutineScope = rememberCoroutineScope()
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
                        BackImage(navController = navController, backTo = "LandOwnerMessages")
                        Spacer(modifier = Modifier.width(20.dp))
                        if(tenantProfile.isNotEmpty()){
                            val ref : StorageReference = FirebaseStorage.getInstance().getReference(tenantProfile)
                            var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) } //storage of image
                            LaunchedEffect(tenantProfile) {
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

                            imageBitmap?.let { img -> //UI of image
                                Image(
                                    bitmap = img,
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(70.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }
                        else{
                            Image(painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                                contentDescription = "User profile mini image" ,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(70.dp)
                            )
                        }
                        Column (
                            modifier = Modifier
                                .width(230.dp)
                        ){
                            Text(text = "$tenantFullName", fontWeight = FontWeight.Bold)
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
                                .fillMaxSize()
                        ){
                            if(totalMessages != null){
                                Column (
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .verticalScroll(state = scrollState) //scrolls at end of loop
                                ){
                                    for (data in totalMessages){ //messages and conditions
                                        val stringTimeStamp = data.timestamp.toString()
                                        val messageLength = data.message.length
                                        //Log.e("Message Length", "$messageLength")
                                        if (data.sender == uid){
                                            //Log.e("Which ran", "User is Sender")
                                            Spacer(modifier = Modifier.height(20.dp))
                                            Column ( //rows for messages
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .heightIn(
                                                        min = 60.dp,
                                                        max = (messageLength * 10).dp
                                                    ),
                                                horizontalAlignment = Alignment.End
                                            ){
                                                Row (
                                                    modifier = Modifier
                                                        .weight(1f)
                                                        .padding(5.dp),
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
                                                                .fillMaxHeight()
                                                                .background(
                                                                    color = Color(color = 0xFFE7B898),
                                                                    shape = RoundedCornerShape(10.dp)
                                                                )
                                                                .padding(start = 15.dp),
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ){
                                                            Text(text = data.message)
                                                            Column (
                                                                modifier = Modifier
                                                                    .fillMaxWidth(),
                                                                horizontalAlignment = Alignment.End
                                                            ){
                                                                Row (
                                                                    modifier = Modifier
                                                                        .fillMaxHeight()
                                                                        .padding(
                                                                            bottom = 5.dp,
                                                                            end = 5.dp
                                                                        ),
                                                                    verticalAlignment = Alignment.Bottom
                                                                ){
                                                                    Text(text = stringTimeStamp, fontSize = 10.sp)
                                                                }
                                                            }
                                                        }

                                                    }
                                                }
                                            }
                                        }
                                        else if (data.sender == tenantUID){
                                            //Log.e("Which ran", "Landlord is Sender ${data.sender}")
                                            Spacer(modifier = Modifier.height(20.dp))
                                            Row ( //rows for messages
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(60.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ){
                                                if(tenantProfile.isNotEmpty()){
                                                    val ref : StorageReference = FirebaseStorage.getInstance().getReference(tenantProfile)
                                                    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) } //storage of image
                                                    LaunchedEffect(tenantProfile) {
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

                                                    imageBitmap?.let { img -> //UI of image
                                                        Image(
                                                            bitmap = img,
                                                            contentDescription = "Profile Picture",
                                                            modifier = Modifier
                                                                .fillMaxHeight()
                                                                .width(70.dp)
                                                                .clip(CircleShape)
                                                        )
                                                    }
                                                }
                                                else{
                                                    Image(painter = painterResource(id = R.drawable.icons8_profile_picture_90),
                                                        contentDescription = "User profile mini image" ,
                                                        modifier = Modifier
                                                            .fillMaxHeight()
                                                            .width(70.dp)
                                                    )
                                                }
                                                Row (
                                                    modifier = Modifier
                                                        .weight(1f)
                                                        .fillMaxHeight()
                                                        .background(
                                                            color = Color(color = 0xFFE7B898),
                                                            shape = RoundedCornerShape(10.dp)
                                                        )
                                                        .padding(start = 15.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ){
                                                    Text(text = data.message,)
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
                                                            Text(text = stringTimeStamp, fontSize = 10.sp)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // Scroll to the bottom (most recent message)
                                    LaunchedEffect(totalMessages.size) {
                                        coroutineScope.launch {
                                            scrollState.animateScrollTo(scrollState.maxValue)
                                        }
                                    }
                                }
                            }
                            else{
                                Text(text = "Start Messaging")
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
                                    contentDescription = "Send Icon",
                                    modifier = Modifier
                                        .clickable(
                                            onClick = {
                                                if(chatInput.isNotEmpty()){
                                                    val chatId = (tenantUID + uid)//will be used vice versa for when landlord messages to store on same document
                                                    val messageData = mapOf(
                                                        "sender" to uid,
                                                        "recipient" to tenantUID,
                                                        "message" to chatInput,
                                                        "tenantID" to tenantUID,
                                                        "landlordID" to uid,
                                                        "timestamp" to System.currentTimeMillis()) //Long value
                                                    db.collection("Messages").document(chatId)
                                                        .collection("chats") //create another collection for storing messageData
                                                        .add(messageData)
                                                        .addOnSuccessListener {
                                                            chatInput = ""
                                                            navController.navigate("LandOwnerSingleMessages?tenantUID=$tenantUID")
                                                        }
                                                }
                                            }
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
