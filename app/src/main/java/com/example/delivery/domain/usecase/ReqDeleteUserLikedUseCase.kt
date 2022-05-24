package com.example.delivery.domain.usecase

import com.example.delivery.data.repository.DbRepository
import com.example.delivery.domain.UseCase

class ReqDeleteUserLikedUseCase(
    private val dbRepository: DbRepository
) : UseCase {
    suspend operator fun invoke(id: Long) = dbRepository.deleteUserLiked(id)
}