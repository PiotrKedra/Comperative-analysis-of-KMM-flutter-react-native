package com.example.kmm_network.android.presentation.user_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val userId: MutableState<Int?> = mutableStateOf(null)

    init {
        // every argument that we are passing here through backStackEntry we can automatically access it here (savedStateHandle) thanks to hilt
        savedStateHandle.get<Int>("userId")?.let { userId ->
            this.userId.value = userId
        }
    }
}