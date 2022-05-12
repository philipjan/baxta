package com.coding.baxta.local.user.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tbl_user")
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val email: String,
    val favoriteAnimal: String,
    val fullName: String,
    val followersCount: Int?,
    val userName: String,
    val dateOfBirth: String
) {
    fun getAge(): Int {
        val date = dateOfBirth.split(" ")[0]
        val year = date.split("-")[0]
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return currentYear - year.toInt()
    }
}