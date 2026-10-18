package com.ktp.questtugasuserinput

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun InputData(modifier: Modifier) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(bottomStart = 25.dp)),
            contentAlignment = Alignment.Center
            ) {
            Image(
                painter = painterResource(id = R.drawable.gamabarbackground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topEnd = 25.dp))
            .background(Color.White),
            contentAlignment = Alignment.Center) {

        }
    }
}