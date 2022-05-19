package com.example.kmm_network.presentation

import com.example.kmm_network.domain.model.User

data class BasicState(
    val isLoading: Boolean = false,
    val user: User? = null
)
