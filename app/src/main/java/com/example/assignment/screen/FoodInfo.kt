package com.example.assignment.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.assignment.Models.NutritionInfoScaled
import com.example.assignment.Models.ServingSize
import com.example.assignment.R
import com.example.assignment.ViewModel.FoodInfoViewModel
import com.example.assignment.ViewModel.Result
import com.example.assignment.ui.theme.LightGrey
import com.example.assignment.ui.theme.LightGrey1
import com.example.assignment.ui.theme.Yellow1


@Composable
fun FoodInfoScreen(foodInfoViewModel: FoodInfoViewModel = hiltViewModel()) {
    val uiState = foodInfoViewModel.uiState.observeAsState()
    when (uiState.value) {
        is Result.Loading -> {
            ShowCircularProgressIndicator()
        }
        is Result.Success -> FoodInfoContent(foodInfoViewModel = foodInfoViewModel)
        is Result.Error -> ShowErrorMessage(message = (uiState.value as Result.Error).message)
        else -> Unit
    }
}

@Composable
fun ShowErrorMessage(message: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Sorry for the inconvenience there is some technical error : $message", fontSize = 36.sp)
    }
}

@Composable
fun ShowCircularProgressIndicator() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}
@Composable
fun FoodInfoContent(foodInfoViewModel: FoodInfoViewModel) {
    val foodInfoData by foodInfoViewModel.foodInfo.collectAsState()
    LazyColumn(Modifier.fillMaxSize().padding(bottom = 96.dp)) {
        item {
            ProductImageView(healthRating = foodInfoData.health_rating)
            Description(foodInfoData.description)
            NutritionTable(foodInfoData.nutrition_info_scaled)
            Facts(foodInfoData.generic_facts)
            SimilarItems()
        }
    }
}

@Composable
fun Description(description : String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 18.dp, vertical = 9.dp)
            .fillMaxWidth()
    ) {
        Title(title = "Description")
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun NutritionTable(nutrientsInfoList : List<NutritionInfoScaled>) {
    Column(modifier = Modifier.padding(horizontal = 18.dp)) {
        Title(title = "Macro Nutrients")
        Card(
            modifier = Modifier
                .padding(horizontal = 1.dp)
                .fillMaxWidth()
                .border(BorderStroke(1.dp, color = Color.Black), shape = RoundedCornerShape(9.dp)),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(LightGrey)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Unit",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Value",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))



                nutrientsInfoList.subList(0,5).forEach { (name,value,units)->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        Text(
                            text = String.format("%.2f", units),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = value,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}


@Composable
fun Title(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 9.dp)
    )
}


@Composable
fun ProductImageView(healthRating : Double) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight / 3)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fried_chicken),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .padding(9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp, 48.dp)
                    .background(
                        Color.Transparent
                    ),
                tint = Color.White
            )
            Text(text = "Back", fontSize = 20.sp, color = Color.White)
        }
        Row(
            modifier = Modifier
                .padding(18.dp)
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Food Information",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Fried Chicken",
                    color = Color.White,
                    style = MaterialTheme.typography.displaySmall
                )
            }
            Card(
                modifier = Modifier
                    .size(72.dp),
                colors = CardDefaults.cardColors(LightGrey1.copy(0.5f)),
                shape = RoundedCornerShape(9.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = healthRating.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "out of 100",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

    }
}

@Composable
fun Facts(facts : List<String>) {
    Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 9.dp)) {
        Title(title = "Facts")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(facts) {fact->
                Fact(
                    fact = fact
                )
            }
        }
    }
}

@Composable
fun Fact(fact: String) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp - 56.dp
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(148.dp)
            .width(screenWidth),
        colors = CardDefaults.cardColors(Yellow1),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Did you know?",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = fact,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SimilarItems() {
    Column(modifier = Modifier.padding(horizontal = 18.dp)) {
        Title(title = "Similar Items")
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(5) {
                SimilarItem()
            }
        }
    }
}

@Composable
fun SimilarItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .size(156.dp, 148.dp),
        colors = CardDefaults.cardColors(Yellow1),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.fried_chicken),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Chicken Tandoori",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 8.dp),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}




