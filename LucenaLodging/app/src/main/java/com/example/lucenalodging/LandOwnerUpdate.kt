package com.example.lucenalodging

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun LandOwnerUpdate(navController: NavHostController, auth : FirebaseAuth, db :FirebaseFirestore){
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
    var selectRoomTitle by remember {
        mutableStateOf("Room For Rent")
    }
    var location by remember {
        mutableStateOf("Purok Happy Valley Enverga Compound Brgy. Ibabang Dupay Lucena City")
    }
    var curfew by remember {
        mutableStateOf("2:00PM")
    }
    var roomIncludes by remember {
        mutableStateOf("Free : Water, Wi-Fi, Bed, Foam, 24 Hours CCTV Footage, 24 Hours Water Supply, Locked Gates, Open Parking Available, Own Comfort Room")
    }
    var locationMaxChar = 180
    var curfewMaxChar = 180
    var roomIncludesMaxChar = 180
    var peopleCount by remember {
        mutableStateOf(2)
    }
    var oneMonthAdvance by remember {
        mutableStateOf(true)
    }
    var oneMonthDeposit by remember {
        mutableStateOf(true)
    }
    var anyID by remember {
        mutableStateOf(false)
    }
    var available by remember {
        mutableStateOf(true)
    }
    var price by remember {
        mutableStateOf("3500")
    }
    var deleteDialog by remember {
        mutableStateOf(false)
    }
    Surface (
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
                                backTo = "LandOwnerEditPost"
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier
                                .padding(top = 5.dp)
                        ) {
                            Text(
                                text = "Update",
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            MainSpacer()
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp)
                        ){
                            Row ( //upload images
                            ){
                                Text(text = "Upload Images",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            }
                            Column ( // room title radio buttons
                                modifier = Modifier
                                    .fillMaxWidth(),

                                ){
                                Row (
                                    verticalAlignment = Alignment.Bottom
                                ){
                                    Text(text = "Select File From Gallery", //needs function to upload image soon
                                        textDecoration = TextDecoration.Underline,
                                        color = Color.Blue
                                    )
                                    Image(painter = painterResource(id = R.drawable.icons8_image_file_30),
                                        contentDescription = "Upload Image",
                                        modifier = Modifier
                                            .width(50.dp)
                                            .height(30.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Row (
                                    modifier = Modifier
                                        .width(250.dp)
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
                                Spacer(modifier = Modifier.height(5.dp))
                                MainSpacer()
                            }
                            Row ( //room title
                            ){
                                Text(text = "Room Title",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            }
                            Column {//room radio buttons
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    RadioButton(selected = selectRoomTitle == "Room For Rent",
                                        onClick = { selectRoomTitle = "Room For Rent"}
                                    )
                                    Text(text = "Room For Rent")
                                    Spacer(modifier = Modifier.width(25.dp))
                                    RadioButton(selected = selectRoomTitle == "Female Bed Spacer",
                                        onClick = { selectRoomTitle = "Female Bed Spacer"}
                                    )
                                    Text(text = "Female Bed Spacer")
                                }
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    RadioButton(selected = selectRoomTitle == "Appartment For Rent",
                                        onClick = { selectRoomTitle = "Appartment For Rent"}
                                    )
                                    Text(text = "Appartment For Rent")
                                    RadioButton(selected = selectRoomTitle == "Male Bed Spacer",
                                        onClick = { selectRoomTitle = "Male Bed Spacer"}
                                    )
                                    Text(text = "Male Bed Spacer")
                                }
                            }
                            MainSpacer()
                            Column {//location
                                Text(text = "Location",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            }
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            ){
                                OutlinedTextField(value = location,
                                    onValueChange = {if (it.length <= locationMaxChar) location = it},
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                        unfocusedContainerColor = Color.White
                                    ),
                                    maxLines = 3, //set max lines for this textfield
                                    supportingText = { //counts max chars
                                        Text(
                                            text = "${location.length} / $locationMaxChar",
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.End,
                                        )
                                    },
                                )
                            }
                            MainSpacer()
                            Column {//Curfew
                                Text(text = "Curfew",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            }
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            ){
                                OutlinedTextField(value = curfew,
                                    onValueChange = {if (it.length <= curfewMaxChar) curfew = it},
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                        unfocusedContainerColor = Color.White
                                    ),
                                    maxLines = 3, //set max lines for this textfield
                                    supportingText = {
                                        Text(
                                            text = "${curfew.length} / $curfewMaxChar",
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.End,
                                        )
                                    },
                                )
                            }
                            MainSpacer()
                            Column {//Includes
                                Text(text = "Room Includes",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            }
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                            ){
                                OutlinedTextField(value = roomIncludes,
                                    onValueChange = {if (it.length <= roomIncludesMaxChar) roomIncludes = it},
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                        unfocusedContainerColor = Color.White
                                    ),
                                    maxLines = 3, //set max lines for this textfield
                                    supportingText = {
                                        Text(
                                            text = "${roomIncludes.length} / $roomIncludesMaxChar",
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.End,
                                        )
                                    },
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            MainSpacer()
                            Row ( //people Count
                            ){
                                Text(text = "Max People Count",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            }
                            Column {//people countradio buttons
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    RadioButton(selected = peopleCount == 1,
                                        onClick = { peopleCount = 1 }
                                    )
                                    Text(text = "1")
                                    RadioButton(selected = peopleCount == 3,
                                        onClick = { peopleCount = 3}
                                    )
                                    Text(text = "3")

                                    RadioButton(selected = peopleCount == 5,
                                        onClick = { peopleCount = 5}
                                    )
                                    Text(text = "5")
                                    RadioButton(selected = peopleCount == 7,
                                        onClick = { peopleCount = 7}
                                    )
                                    Text(text = "7")
                                    RadioButton(selected = peopleCount == 9,
                                        onClick = { peopleCount = 9}
                                    )
                                    Text(text = "9")
                                }
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    RadioButton(selected = peopleCount == 2,
                                        onClick = { peopleCount = 2}
                                    )
                                    Text(text = "2")
                                    RadioButton(selected = peopleCount == 4,
                                        onClick = { peopleCount = 4}
                                    )
                                    Text(text = "4")
                                    RadioButton(selected = peopleCount == 6,
                                        onClick = { peopleCount = 6}
                                    )
                                    Text(text = "6")
                                    RadioButton(selected = peopleCount == 8,
                                        onClick = { peopleCount = 8}
                                    )
                                    Text(text = "8")
                                    RadioButton(selected = peopleCount == 10,
                                        onClick = { peopleCount = 10}
                                    )
                                    Text(text = "10")

                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            MainSpacer()
                            Row ( //renting requirement
                            ){
                                Text(text = "Renting Requirement",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            }
                            Column {//renting requirement check
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Checkbox(
                                        checked = oneMonthAdvance,
                                        onCheckedChange = {oneMonthAdvance = it}
                                    )
                                    Text(text = "One Month Advance")
                                    Checkbox(
                                        checked = oneMonthDeposit,
                                        onCheckedChange = {oneMonthDeposit = it}
                                    )
                                    Text(text = "One Month Deposit")
                                }
                                Row (
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Checkbox(
                                        checked = anyID,
                                        onCheckedChange = {anyID = it}
                                    )
                                    Text(text = "Any ID")
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            MainSpacer()
                            Row ( //availability
                            ){
                                Text(text = "Availability",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            }
                            Column {//available check
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = available,
                                        onCheckedChange = { available = it }
                                    )
                                    Text(text = "Available?")
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            MainSpacer()
                            Row ( //monthly price
                            ){
                                Text(text = "Monthly Price",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            }
                            Column {//monthly price
                                Row(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(50.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    OutlinedTextField(value = price,
                                        onValueChange = { if(it.isDigitsOnly()) price = it},
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        colors = TextFieldDefaults.colors( //removes extra background color of label in this outlinedTextField
                                            unfocusedContainerColor = Color.White
                                        ),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), //changes keyboard to numbers
                                        singleLine = true, //set max lines for this textfield
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            MainSpacer()
                            Spacer(modifier = Modifier.height(10.dp))
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Row (
                                ){
                                    OutlinedButton(
                                        onClick = {
                                                  navController.navigate("LandOwnerUpdatedPost")
                                        },//soon navigates
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398)),
                                        modifier = Modifier
                                            .width(150.dp)
                                    ) {
                                        Text(text = "Update", color = Color.Black)
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    OutlinedButton(
                                        onClick = {
                                                  deleteDialog = true
                                        },//soon navigates
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFFF2B398)),
                                        modifier = Modifier
                                            .width(150.dp)
                                    ) {
                                        Text(text = "Delete", color = Color.Black)
                                    }
                                    if (deleteDialog){
                                        AlertDialogDelete(
                                            onDismissRequest = { deleteDialog = false },
                                            onConfirmation = { deleteDialog = false },
                                            dialogTitle = "Confirm Delete Action?",
                                            dialogText = "The data will be permanently Deleted",
                                            navController = navController,
                                        )
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
fun AlertDialogDelete(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    navController: NavHostController,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                    navController.navigate("LandOwnerDelete")
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}
