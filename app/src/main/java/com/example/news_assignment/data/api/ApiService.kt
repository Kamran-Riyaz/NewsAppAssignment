package com.example.news_assignment.data.api

import com.example.news_assignment.data.model.NewsResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest-news_p")
    fun getLatestNews(@Query("page_number") pageNumber: Int): Call<NewsResponseModel>

}