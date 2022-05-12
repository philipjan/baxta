package com.coding.baxta.model.user

import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Id(
    @SerialName("\$oid")
    val uniqueId: String
)