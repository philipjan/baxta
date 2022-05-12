package com.coding.baxta.local.user.entity

import com.coding.baxta.exceptions.ErrorResponse

sealed class UserState {
    data class GetUserListSuccess(val users: List<User>) : UserState()
    data class GetUserInfoSuccess(val user: User) : UserState()
    data class Error(val error: ErrorResponse?) : UserState()
}