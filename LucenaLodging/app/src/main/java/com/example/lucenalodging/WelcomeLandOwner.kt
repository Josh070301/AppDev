package com.example.lucenalodging

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

@Composable
fun WelcomeLandOwner(navController: NavHostController, auth: FirebaseAuth, db : FirebaseFirestore){
    val uid = auth.currentUser?.uid
    var fullName by remember {
        mutableStateOf("")
    }
    data class Post(
        val email : String,
        val selectRoomTitle : String,
        val location : String,
        val curfew : String,
        val roomIncludes : String,
        val peopleCount : Int,
        val oneMonthAdvance : Boolean,
        val oneMonthDeposit : Boolean,
        val anyID : Boolean,
        val available : Boolean,
        val price : String,
        val selectImages : List<Uri>,
    )
    var warn by remember {
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
    val postLists = mutableListOf<Post>()
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color(color = 0xFFFDF7E4)
    ){
        BottomMenu(navController,fullName, usage ="Browse Post", userType = "LandOwner")//scaffold on ScaffoldAndEtc.kt
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
                        text = "Your Posts $fullName",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp),
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    if (uid != null){
                        db.collection("Users").document(uid).collection("Posts")
                            .get()
                            .addOnSuccessListener { doc ->
                                for (document in doc.documents){
                                    warn = "Double Success"
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
                                    val selectImages = document.get("images") as? List<String> ?: emptyList()

                                    val imagesList = selectImages.map { Uri.parse(it) } // converts string to Uri

                                    val storagePost = Post(
                                        email,
                                        selectRoomTitle,
                                        location, curfew,
                                        roomIncludes, peopleCount,
                                        oneMonthAdvance,
                                        oneMonthDeposit,
                                        anyID,
                                        available,
                                        price,
                                        imagesList,
                                    )
                                    postLists.add(storagePost)
                                }

                            }
                            .addOnFailureListener { e->
                                warn = "Error getting data"
                            }
                        Text(text = "$postLists")
                        Text(text = "$warn")
                        postLists.forEachIndexed { index, num ->
                            userContent(
                                navController,
                                fullName,
                                userType = "LandOwner",
                                postLists[index-1].anyID,
                                postLists[index-1].available,
                                postLists[index-1].curfew,
                                postLists[index-1].selectImages,
                                postLists[index-1].location,
                                postLists[index-1].oneMonthAdvance,
                                postLists[index-1].oneMonthDeposit,
                                postLists[index-1].peopleCount,
                                postLists[index-1].price,
                                postLists[index-1].roomIncludes,
                                postLists[index-1].selectRoomTitle
                            )
                        }
                    }
                }
            }
        }
    }
}
