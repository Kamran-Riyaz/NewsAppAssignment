package com.example.news_assignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news_assignment.data.api.GenericResponse
import com.example.news_assignment.data.repository.NewsRepository
import com.example.news_assignment.data.model.NewsResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsData = MutableLiveData<GenericResponse<NewsResponseModel>>()
    val newsData: LiveData<GenericResponse<NewsResponseModel>> = _newsData

    fun getLatestNews(pageNumber: Int) {
        newsRepository.getLatestNews(pageNumber).enqueue(object : Callback<NewsResponseModel> {
            override fun onResponse(
                call: Call<NewsResponseModel>,
                response: Response<NewsResponseModel>
            ) {
                if (response.isSuccessful) {
                    _newsData.value = GenericResponse(response.body(), true, null)
                } else {
                    _newsData.value = GenericResponse(null, false, response.message())
                }
            }

            override fun onFailure(call: Call<NewsResponseModel>, t: Throwable) {
                _newsData.value = GenericResponse(null, false, t.message)
            }

        })
    }
}