package com.example.assignment.network

import com.example.assignment.Models.FoodPageResponse
import com.example.assignment.Models.HomePageResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    companion object{
        const val BASE_URL = "http://52.25.229.242:8000"
    }

    @GET("/homepage_v2")
    suspend fun getHomepageData() : Response<HomePageResponse>

    @GET("/food_info")
    suspend fun getFoodInfo() : Response<FoodPageResponse>

}