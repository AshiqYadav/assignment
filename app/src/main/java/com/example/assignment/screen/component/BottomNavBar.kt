package com.example.assignment.screen.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.assignment.utils.BottomNavBarConstants
@Composable
fun BottomNavBar(navController: NavController) {
    Column {
        Divider(
            color = Color.Black,
            thickness = 2.dp
        )
        NavigationBar(
            modifier = Modifier.height(96.dp),
            containerColor = Color.Gray.copy(0.3f)  // Make bottom bar background transparent
        ) {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            BottomNavBarConstants.BottomNavItem.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { startRoute ->
                                popUpTo(startRoute) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.label,
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp)) // Adds space between icon and text
                            Text(
                                text = item.label,
                                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                                color = Color.Black.copy(0.8f)
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.LightGray.copy(0.001f)
                    )
                )
            }
        }
    }
}

