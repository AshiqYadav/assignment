package com.example.assignment.utils

import com.example.assignment.Models.BottomNavItem
import com.example.assignment.NavigationGraph.Screen
import com.example.assignment.R

object BottomNavBarConstants {
    val BottomNavItem = listOf(
        BottomNavItem(
            label = "Activity",
            icon = R.drawable.activity,
            route = Screen.ACTIVITY
        ),
        BottomNavItem(
            label = "Goals",
            icon = R.drawable.goals,
            route = Screen.GOALS
        ),
        BottomNavItem(
            label = "Camera",
            icon = R.drawable.camera,
            route = Screen.CAMERA
        ),
        BottomNavItem(
            label = "Feed",
            icon = R.drawable.feed,
            route = Screen.FEED
        ),
        BottomNavItem(
            label = "Profile",
            icon = R.drawable.profile,
            route = Screen.PROFILE
        )
    )
}