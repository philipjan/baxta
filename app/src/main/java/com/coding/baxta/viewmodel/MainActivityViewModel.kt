package com.coding.baxta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.baxta.exceptions.ErrorResponse
import com.coding.baxta.local.user.entity.UserState
import com.coding.baxta.usecase.GetUsersUseCase
import com.coding.baxta.usecase.WatchUserUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivityViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val watchUserUseCase: WatchUserUseCase
) : ViewModel() {

    private val mutableUserState = MutableSharedFlow<UserState>()
    val userState = mutableUserState.asSharedFlow()

    init {
        viewModelScope.launch(
            CoroutineExceptionHandler { coroutineContext, throwable ->
                showErrorMessage(throwable)
            }
        ) {
            getUsers()
            getUsersUseCase
                .watchUsersList()
                .distinctUntilChanged()
                .collectLatest {
                    mutableUserState.emit(
                        UserState.GetUserListSuccess(it)
                    )
                }
        }
    }

    private fun getUsers() {
        viewModelScope.launch(
            CoroutineExceptionHandler { coroutineContext, throwable ->
                showErrorMessage(throwable)
            }
        ) {
            getUsersUseCase.getUsers()
        }
    }

    private fun showErrorMessage(throwable: Throwable) {
        viewModelScope.launch {
            mutableUserState.emit(
                UserState
                    .Error(
                        ErrorResponse(
                            message = throwable.message,
                            throwable = throwable
                        )
                    )
            )
        }
    }

    fun watchUserInfo(userId: String) {
        viewModelScope.launch(
            CoroutineExceptionHandler { coroutineContext, throwable ->
                showErrorMessage(throwable)
            }
        ) {
            mutableUserState.emit(
                UserState
                    .GetUserInfoSuccess(watchUserUseCase.getUserInfo(userId))
            )
        }
    }

}

