package com.example.assignment.Models

data class FoodPageResponse(
    val `data`: DataX = DataX(),
    val message: String = "",
    val success: Boolean = false
)