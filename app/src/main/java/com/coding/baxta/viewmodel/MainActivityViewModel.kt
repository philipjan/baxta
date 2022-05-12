package com.coding.baxta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.baxta.exceptions.ErrorResponse
import com.coding.baxta.local.user.entity.UserState
import com.coding.baxta.usecase.GetUserUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivityViewModel(private val getUserUseCase: GetUserUseCase) : ViewModel() {

    private val mutableUserState = MutableSharedFlow<UserState>()
    val userState = mutableUserState.asSharedFlow()

    init {
        viewModelScope.launch(
            CoroutineExceptionHandler { coroutineContext, throwable ->
                showErrorMessage(throwable)
            }
        ) {
            getUsers()
            getUserUseCase
                .watchUsersList()
                .distinctUntilChanged()
                .collectLatest {
                    emitState {
                        mutableUserState.emit(
                            UserState.GetUserSuccess(it)
                        )
                    }
                }
        }
    }

    private fun getUsers() {
        viewModelScope.launch(
            CoroutineExceptionHandler { coroutineContext, throwable ->
                showErrorMessage(throwable)
            }
        ) {
            getUserUseCase.getUsers()
        }
    }

    private fun showErrorMessage(throwable: Throwable) {
        emitState {
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

    private fun emitState(
        action: suspend () -> Unit
    ) = runBlocking {
        action()
    }
}

