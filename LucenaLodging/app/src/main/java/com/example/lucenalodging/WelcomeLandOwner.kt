package com.example.lucenalodging

import android.content.ClipData.Item
import android.media.RouteListingPreference
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import org.w3c.dom.Text

@Composable
fun WelcomeLandOwner(navController: NavHostController, auth: FirebaseAuth, db : FirebaseFirestore){
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

    if (uid != null) {
        db.collection("Users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    fullName = document.getString("fullName").toString()
                }
            }
        db.collection("LandLordPost").whereEqualTo("uid", uid)
            .get()
            .addOnSuccessListener { doc ->
                posts.clear() // clears the current list to avoid duplication
                if (doc != null){
                    var x = 0
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
                        x++
                        warn = x.toString()
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
                    for (data in posts){
                        userContent(
                            navController,
                            fullName,
                            userType = "LandOwner",
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
                            posterName = data.fullName,
                            userProfile = data.userProfile
                        )
                    }
                        //Text(text = "${postLists[0].anyID}${postLists[0].available}${postLists[0].curfew}${postLists[0].selectImages}${postLists[0].location}${postLists[0].oneMonthAdvance}${postLists[0].oneMonthDeposit}${postLists[0].peopleCount}${postLists[0].price}${postLists[0].roomIncludes}${postLists[0].selectRoomTitle} ")
                    }
                }
            }
        }
    }

