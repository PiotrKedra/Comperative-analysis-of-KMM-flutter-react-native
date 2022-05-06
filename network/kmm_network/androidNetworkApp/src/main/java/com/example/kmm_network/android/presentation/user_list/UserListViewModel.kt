package com.example.kmm_network.android.presentation.user_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, // we don't need it here, but hilt requires it?
) : ViewModel() {
}