package com.example.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuView(coins:Int,onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF006CFF)), horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier.padding(start = 260.dp, top = 109.dp)) {
            Image(
                painter = painterResource(id = R.drawable.coin_3d_2),
                contentDescription = "Coin",
                modifier = Modifier
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = if (coins > 0) coins.toString() else "0",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color.White
                )
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logotype),
            contentDescription = "logo",
            modifier = Modifier
                .padding(top = 105.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

        Button(onClick = { onClick() }, modifier = Modifier
            .width(170.dp)
            .padding(top = 200.dp)
            , colors = ButtonDefaults.buttonColors(Color(0xFFE3FF37))) {
            Text(text = "PLAY", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight(600),color = Color(0xFF28248C)))
        }

        Image(painter = painterResource(id =R.drawable.privacy), contentDescription = "privacy-policy", modifier = Modifier.padding(top=150.dp, start = 250.dp))
    }

}

@Preview
@Composable
fun Opr(){
    MenuView(coins = 10) {

    }
}