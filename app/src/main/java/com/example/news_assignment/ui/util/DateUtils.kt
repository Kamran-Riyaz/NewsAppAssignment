package com.example.news_assignment.ui.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp)
    val formatter = SimpleDateFormat("MMM-dd-yyyy", Locale.ENGLISH)
    return formatter.format(date)
}
