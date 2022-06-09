package com.example.delivery.domain.usecase

import com.example.delivery.data.repository.UserRepository
import com.example.delivery.domain.UseCase

class GetUserLocationUseCase(
    private val userRepository: UserRepository
) : UseCase {
    operator fun invoke() = userRepository.getUserLocation()
}