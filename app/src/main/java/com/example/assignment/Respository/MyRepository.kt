package com.example.assignment.Respository

import com.example.assignment.Models.FoodPageResponse
import com.example.assignment.Models.HomePageResponse
import com.example.assignment.network.NetworkService
import javax.inject.Inject

class MyRepository @Inject constructor(private val networkService: NetworkService) {

    suspend fun getHomePageData() : HomePageResponse? {
        return networkService.getHomepageData().body()
    }

    suspend fun getFoodInfo() : FoodPageResponse?{
        return networkService.getFoodInfo().body()
    }

}