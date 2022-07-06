package com.example.kmm_network.domain

data class DataState<T>(
    val message: String? = null,
    val data: T? = null,
    val isLoading: Boolean = false
) {

    companion object {

        fun <T> success(
            data: T? = null,
            message: String? = null,
        ): DataState<T> {
            return DataState(
                data = data,
                message = message
            )
        }

        fun <T> error(
            message: String,
        ): DataState<T> {
            return DataState(message = message)
        }

        fun <T> loading(): DataState<T> {
            return DataState(isLoading = true)
        }
    }
}
