package com.example.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.min
import kotlin.math.max

@Composable
fun GameScene(function: () -> Unit,onCoinsChanged: (Int) -> Unit) {
    val images = listOf(
        R.drawable.anchor,
        R.drawable.coin,
        R.drawable.beach_ball,
        R.drawable.dollar,
        R.drawable.hawaiian ,
        R.drawable.ice_cream,
        R.drawable.snorkel,
        R.drawable.suitcase)
    val cards = remember { mutableStateListOf(*(images + images).shuffled().toTypedArray()) }
    val openCards = remember { mutableStateListOf<Int>() }
    val matchedCards = remember { mutableStateListOf<Int>() }
    val startTime = remember { mutableStateOf<Long?>(null) }
    val coins = remember { mutableIntStateOf(0) }

    val elapsedTime = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)
            elapsedTime.value = ((System.currentTimeMillis() - startTime.value!!) / 1000).toInt()
        }
    }

    LaunchedEffect(key1 = coins.value) {
        onCoinsChanged(coins.value)
    }


    if (startTime.value == null) {
        startTime.value = System.currentTimeMillis()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF006CFF)), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp, bottom = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Timer: ${elapsedTime.value}s" , style = TextStyle(fontSize = 18.sp, color = Color.White
            ), modifier = Modifier.padding(top=100.dp))
            Row(modifier = Modifier.padding(top=100.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.coin_3d_2),
                    contentDescription = "Coin",
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(
                    text = "0",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color.White
                    )
                )
            }
        }
        for (i in cards.indices step 4) {
            Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
                for (j in i until min(i + 4, cards.size)) {
                    MemoryCard(cards[j], openCards.contains(j) || matchedCards.contains(j)) { onCardClick(j, openCards, matchedCards, cards) }
                }
            }
        }
    }

    if (matchedCards.size == cards.size) {
        val timeSpent = elapsedTime.value
        coins.value = calculateReward(timeSpent)
        function()
    }
}

@Composable
fun MemoryCard(image: Int, isOpen: Boolean, onClick: () -> Unit) {
    Box(modifier = Modifier
        .size(80.dp)
        .padding(8.dp)
        .clickable(onClick = onClick)
        .clip(RoundedCornerShape(12.dp))) {
        if (isOpen) {
            Image(painter = painterResource(id = image), contentDescription = null)
        } else {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0056CC)))
        }
    }
}

fun onCardClick(index: Int, openCards: MutableList<Int>, matchedCards: MutableList<Int>, cards: List<Int>) {
    openCards.add(index)
    if (openCards.size == 2) {
        if (cards[openCards[0]] == cards[openCards[1]]) {
            matchedCards.add(openCards[0])
            matchedCards.add(openCards[1])
        }
        openCards.clear()
    }
}

fun calculateReward(timeSpent: Int): Int {
    return when {
        timeSpent <= 20 -> 100
        timeSpent > 20 -> max(10, 100 - (timeSpent - 20) * 5)
        else -> 10
    }
}
