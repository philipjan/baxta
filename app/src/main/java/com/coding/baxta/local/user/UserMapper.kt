package com.coding.baxta.local.user

import com.coding.baxta.model.user.User

object UserMapper {
    fun List<User>.mapToEntityUsers(): List<com.coding.baxta.local.user.entity.User> {
        return this.map {
            com.coding.baxta.local.user.entity.User(
                id = it.id.uniqueId,
                email = it.email,
                favoriteAnimal = it.favoriteAnimal,
                fullName = "${it.firstName} ${it.lastName}",
                followersCount = it.numberOfFollowers,
                userName = it.username,
                dateOfBirth = it.dob
            )
        }
    }
}