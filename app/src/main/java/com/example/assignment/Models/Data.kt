package com.example.assignment.Models

data class Data(
    val section_1: Section1 = Section1(),
    val section_2: Section2 = Section2(),
    val section_3: Section3 = Section3(),
    val section_4: Section4 = Section4()
)

data class Section1(
    val plan_name: String = "",
    val progress: String = ""
)

data class Section2(
    val accuracy: String = "",
    val calories_burned: Int = 0,
    val reps: Int = 0,
    val workout_duration: String = ""
)

data class Section3(
    val plan_1: Plan1 = Plan1(),
    val plan_2: Plan1 = Plan1()
)

data class Section4(
    val category_1: Category1 = Category1(),
    val category_2: Category1 = Category1()
)

data class Plan1(
    val difficulty: String = "",
    val plan_name: String = ""
)

data class Category1(
    val category_name: String = "",
    val no_of_exercises: String = ""
)
