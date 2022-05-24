package com.example.delivery.data.repository

import com.example.delivery.data.response.GithubData
import com.example.delivery.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class  ApiRepositoryImpl(
    private val service: ApiService
) : ApiRepository {

    override fun getGitHub(): Flow<List<GithubData>> = flow {
        emit(service.getGithub())
    }.map { it.items }

}