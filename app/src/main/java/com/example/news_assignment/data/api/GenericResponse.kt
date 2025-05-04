package com.example.news_assignment.data.api

data class GenericResponse<T>(
    val data: T?,
    val success: Boolean,
    val message: String?,
)