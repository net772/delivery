package com.example.delivery.data.repository

import com.example.delivery.data.response.GithubData
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getGitHub(): Flow<List<GithubData>>
}