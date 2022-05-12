package com.coding.baxta.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val dob: String,
    val email: String,
    @SerialName("favorite animal")
    val favoriteAnimal: String,
    @SerialName("first_name")
    val firstName: String,
    val id: Id,
    val lastName: String,
    @SerialName("no_of_followers")
    val numberOfFollowers: Int,
    val username: String
)