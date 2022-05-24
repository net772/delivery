package com.example.delivery.domain.usecase

import com.example.delivery.data.entity.UserLikeEntity
import com.example.delivery.data.repository.DbRepository
import com.example.delivery.domain.UseCase

class ReqInsertUserLikeUseCase(
    private val dbRepository: DbRepository
) : UseCase {
    suspend operator fun invoke(userLikeEntity: UserLikeEntity) = dbRepository.insertUserLiked(userLikeEntity)
}
