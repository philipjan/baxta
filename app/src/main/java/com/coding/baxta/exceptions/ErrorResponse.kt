package com.coding.baxta.exceptions

data class ErrorResponse(
    val message: String? = null,
    val throwable: Throwable? = null
)
