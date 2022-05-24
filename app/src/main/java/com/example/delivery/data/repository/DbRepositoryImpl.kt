package com.example.delivery.data.repository

import com.example.delivery.data.db.dao.UserLikeDao
import com.example.delivery.data.entity.UserLikeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DbRepositoryImpl(
    private val userLikeDao: UserLikeDao
) : DbRepository {

    override fun getUserAllLike(): Flow<List<UserLikeEntity>> = flow {
        emit( userLikeDao.getAll())
    }.map { it }


    override fun getUserLike(id: Long): Flow<UserLikeEntity> {
        return  flow {
            emit(userLikeDao.get(id))
        }.map { it }

    }
    override suspend fun insertUserLiked(userLikeEntity: UserLikeEntity) {
        userLikeDao.insert(userLikeEntity)
    }

    override suspend fun deleteUserLiked(id: Long) {
        userLikeDao.delete(id)
    }

}