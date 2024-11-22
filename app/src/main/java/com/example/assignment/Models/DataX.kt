package com.example.assignment.Models

data class DataX(
    val _id: String = "",
    val api_name: List<String> = emptyList(),
    val badge_indicator: String = "",
    val credits_url: String = "",
    val cuisine: String = "",
    val default_unit_serving: String = "",
    val description: String = "",
    val generic_facts: List<String> = emptyList(),
    val health_rating: Double = 0.0,
    val image: String = "",
    val image_url: String = "",
    val ingredients: List<Ingredient> = emptyList(),
    val itemtype: String = "",
    val name: String = "",
    val no_of_servings: Double = 0.0,
    val nutrition_facts: String = "",
    val nutrition_info: String = "",
    val nutrition_info_scaled: List<NutritionInfoScaled> = emptyList(),
    val serving_sizes: List<ServingSize> = emptyList(),
    val similar_items: List<SimilarItem> = emptyList(),
    val type: String = ""
)

data class Ingredient(
    val ingid: String = "",
    val name: String = "",
    val value: Double = 0.0
)

data class NutritionInfoScaled(
    val name: String = "",
    val units: String = "",
    val value: Double = 0.0
)

data class ServingSize(
    val name: String = "",
    val units: String = "",
    val value: Double = 0.0
)

data class SimilarItem(
    val _id: String = "",
    val image: String = "",
    val name: String = ""
)
