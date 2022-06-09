package com.example.delivery.data.response.search

import com.example.delivery.data.response.search.Pois

data class SearchPoiInfo(
    val totalCount: String,
    val count: String,
    val page: String,
    val pois: Pois
)
