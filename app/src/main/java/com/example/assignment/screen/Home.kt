package com.example.assignment.screen

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.assignment.R
import com.example.assignment.ViewModel.HomeScreenViewModel
import com.example.assignment.ViewModel.Result

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {
    val uiState = homeScreenViewModel.uiState.observeAsState()
    when (uiState.value) {
        is Result.Loading -> ShowCircularProgressIndicator()
        is Result.Success -> ShowHomeScreenContent(homeScreenViewModel)
        is Result.Error -> ShowErrorMessage(message = (uiState.value as Result.Error).message)
        else -> Unit
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowHomeScreenContent(homeScreenViewModel: HomeScreenViewModel) {
    val homeScreenData by homeScreenViewModel.homeScreenData.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 96.dp, top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            HomeScreenHeader()
        }
        item {
            CurrentDateSection(homeScreenViewModel)
        }
        item {
            GoalProgressIndicator()
        }
        item {
            GoalDetailsBody()
        }
        item {
            UserGoalsSection(homeScreenData.section_1.plan_name)
        }
        item {
            ExploreMoreSection()
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentDateSection(homeScreenViewModel: HomeScreenViewModel) {
    val dayText by homeScreenViewModel.dayText.collectAsState()
    val dateText by homeScreenViewModel.dateText.collectAsState()
    Column (modifier = Modifier.padding(vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = dateText,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = dayText,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun HomeScreenHeader() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dietsnap",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFFFFA500)
            )
            Row {
                IconButton(onClick = { /* Notification Action */ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications"
                    )
                }
                IconButton(onClick = { /* Diet Action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.medal),
                        contentDescription = "Diet Icon",
                        modifier = Modifier.size(16.dp)
                    )
                }
                IconButton(onClick = { /* Menu Action */ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            }
        }
        Divider(color = DividerDefaults.color.copy(0.4f))
    }
}

@Composable
fun GoalProgressIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(200.dp)
            .border(
                BorderStroke(8.dp, Color(0xFFFFA500)), // Outer Circle
                shape = CircleShape
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(160.dp)
                .border(
                    BorderStroke(8.dp, Color(0xFF800080)), // Inner Circle
                    shape = CircleShape
                )
        ) {
            Text(
                text = "SET GOAL!",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun GoalDetailsBody() {
    Row(modifier = Modifier.padding(vertical = 16.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.ic_diet),
            contentDescription = null,
            tint = Color(0xFFFFA500)
        )
        Text(
            text = "Diet Pts",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 4.dp),
            fontWeight = FontWeight.SemiBold
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_exercise),
            contentDescription = null,
            tint = Color(0xFF800080)
        )
        Text(
            text = "Exercise Pts",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 4.dp),
            fontWeight = FontWeight.SemiBold
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "1500",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFAA057)
            )
            Text(text = "Cal", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "3/5",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFAA057)
            )
            Text(text = "Days", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "88",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFAA057)
            )
            Text(
                text = "Health Score",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Page Indicator
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(Color(0xFFFFA500), shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(Color.Gray, shape = CircleShape) // Highlighted
        )
    }
}


@Composable
fun UserGoalsSection(message: String) {
    Column(Modifier.fillMaxWidth()) {
        Title("Your Goals")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(9.dp))
                .background(Color(0xBAE0E0E9))
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fitness),
                contentDescription = null, modifier = Modifier
                    .clip(RoundedCornerShape(9.dp))
                    .size(56.dp)
            )
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(
                    text = message,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Current Major Goal",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ExploreMoreSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 9.dp)
    ) {
        Title("Explore")
        Spacer(modifier = Modifier.height(18.dp))
        val items = listOf(
            Pair(
                R.drawable.women to "Find Diets",
                "Find premade diets according to your cuisine"
            ),
            Pair(
                R.drawable.women1 to "Find Nutritionist",
                "Get a customized diet to achieve your health goal"
            )
        )
        items.forEach { (imageAndTitle, description) ->
            ExploreItem(
                imageRes = imageAndTitle.first,
                title = imageAndTitle.second,
                description = description
            )
        }
    }
}

@Composable
fun ExploreItem(imageRes: Int, title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

