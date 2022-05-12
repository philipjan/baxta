package com.coding.baxta.local.user.entity

import com.coding.baxta.exceptions.ErrorResponse

sealed class UserState {
    data class GetUserSuccess(val users: List<User>) : UserState()
    data class Error(val error: ErrorResponse?) : UserState()
}