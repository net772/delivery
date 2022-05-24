package com.example.delivery.domain.usecase

import com.example.delivery.data.repository.ApiRepository
import com.example.delivery.domain.UseCase

class GetGithubUseCase(
    private val apiRepository: ApiRepository
) : UseCase {
    operator fun invoke() = apiRepository.getGitHub()
}