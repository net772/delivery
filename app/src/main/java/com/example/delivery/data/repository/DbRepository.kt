package com.example.delivery.data.repository

import com.example.delivery.data.entity.UserLikeEntity
import kotlinx.coroutines.flow.Flow

interface DbRepository {
    fun getUserAllLike(): Flow<List<UserLikeEntity>>
    fun getUserLike(id: Long): Flow<UserLikeEntity>
    suspend fun insertUserLiked(userLikeEntity: UserLikeEntity)
    suspend fun deleteUserLiked(id: Long)
}