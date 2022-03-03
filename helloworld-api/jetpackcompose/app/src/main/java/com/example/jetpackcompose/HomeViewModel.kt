package com.example.jetpackcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.data.Data
import com.example.jetpackcompose.data.FearAndGreedIndex
import com.example.jetpackcompose.data.Metadata
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: Repo
) : ViewModel() {

    private val _state = MutableStateFlow<FearAndGreedIndex>(
        FearAndGreedIndex(
            listOf(Data("..", "..", "..", "..")),
            Metadata(""),
            "null")
        )
    val state: StateFlow<FearAndGreedIndex> = _state

    init {
        viewModelScope.launch {
            val data = repo.getIndex()
            _state.value = data
        }
    }
}