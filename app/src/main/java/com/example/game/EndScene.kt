package com.example.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EndScene(coins: Int, function: (Int) -> Unit) {
    var coins = remember { mutableStateOf(coins)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF006CFF)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(painter = painterResource(id = R.drawable.cup), contentDescription ="cup", modifier = Modifier.padding(top=100.dp) )
        Text(text = "[CONGRATULATION!]" , style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight(600), color = Color.White), modifier = Modifier)
        Text(text = "[Great you won!!]" , style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight(400), color = Color.White))
        Row(
            modifier = Modifier
                .size(width = 300.dp, height = 80.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color(0xFF006CFF))
                .clip(shape=RoundedCornerShape(topStart = 12.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.coin_3d_2), contentDescription = "coin_value")
            Text(text = "${coins.value}", style = TextStyle(fontSize = 24.sp,color=Color.White), modifier = Modifier.padding(start=2.dp))
        }
        Row(modifier = Modifier
            .size(width = 300.dp, height = 100.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically){
            Button(
                onClick = {coins.value=coins.value*2}, modifier = Modifier
                    .size(width = 170.dp, height = 70.dp),colors = ButtonDefaults.buttonColors(Color(0xFFE3FF37))
            ) {
                Text(text = "Double Reward" , style = TextStyle(fontSize = 18.sp,color = Color(0xFF28248C)))
            }
            Button(
                onClick = { function(coins.value) },
                modifier = Modifier.size(width = 100.dp, height = 70.dp),colors = ButtonDefaults.buttonColors(Color(0xFFE3FF37))){
                    Text(text = "Home" , style = TextStyle(fontSize = 18.sp,color = Color(0xFF28248C)))
                }
        }
    }
}

@Preview
@Composable
fun Preview() {
    EndScene(coins = 2) {

    }
}
