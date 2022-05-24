package com.example.delivery.data.network

import com.example.delivery.data.response.GithubResponse
import retrofit2.http.GET

interface ApiService {

    @GET("search/users?q=shop")
    suspend fun getGithub(): GithubResponse

}