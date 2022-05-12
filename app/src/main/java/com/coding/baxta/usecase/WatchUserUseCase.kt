package com.coding.baxta.usecase

import com.coding.baxta.repository.user.IUserRepository

class WatchUserUseCase(private val userRepository: IUserRepository) {
    fun watchUserInfoFlow(userId: String) =
        userRepository.watchUserFlow(userId)

    suspend fun getUserInfo(userId: String) =
        userRepository.watchUser(userId)
}