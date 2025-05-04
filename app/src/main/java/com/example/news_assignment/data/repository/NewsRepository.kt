package com.example.news_assignment.data.repository

import com.example.news_assignment.data.api.ApiService
import com.example.news_assignment.data.model.NewsResponseModel
import retrofit2.Call
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService) {

    fun getLatestNews(pageNumber: Int): Call<NewsResponseModel> {
        return apiService.getLatestNews(pageNumber)
    }

}