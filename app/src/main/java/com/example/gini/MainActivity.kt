package com.example.gini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.gini.ui.theme.GiniTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GiniTheme {
                ImagesComponent()
            }
        }
    }
}

@Composable
fun ImagesComponent() {
    val viewModel = viewModel<GiniViewModel>()

    val images by viewModel.allImages.observeAsState(initial = emptyList())


    LazyColumn{
        items(images){ image ->
            Box (
                modifier = Modifier.padding(start = 7.dp, top = 7.dp, bottom = 7.dp, end = 7.dp)
            ){
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    border = BorderStroke(2.dp, Color.Black)
                ){
                    AsyncImage(
                        model = image.uri,
                        contentDescription = "Gini-Apps :)",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .aspectRatio(16f / 9f)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }
                Row(
                    modifier = Modifier
                        .offset(x = 6.dp, y = 6.dp)
                        .background(
                            color = Color(0x80FFFFFF),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(15.dp, 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Likes",
                        modifier = Modifier
                            .size(25.dp),
                        tint = Color.Red
                    )
                    Text(
                        text = image.likes.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Comments",
                        modifier = Modifier
                            .size(25.dp),
                        tint = Color.Yellow
                    )
                    Text(
                        text = image.comments.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}
