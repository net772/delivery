package com.example.delivery.domain.usecase

import com.example.delivery.data.repository.DbRepository
import com.example.delivery.domain.UseCase

class GetUserLikeUseCase(
    private val dbRepository: DbRepository
) : UseCase {
    operator fun invoke(id: Long) = dbRepository.getUserLike(id)
}