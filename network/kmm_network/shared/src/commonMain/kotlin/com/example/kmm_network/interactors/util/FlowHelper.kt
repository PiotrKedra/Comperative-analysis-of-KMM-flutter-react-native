package com.example.kmm_network.interactors.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>): Flow<T> by origin {

    fun collectCommon(
        coroutineScope: CoroutineScope? = null,
        callback: (T) -> Unit
    ) {
        origin.onEach {
            callback(it)
        }.launchIn(coroutineScope ?: CoroutineScope(Dispatchers.Main))
    }
}

//object MainLoopDispatcher: CoroutineDispatcher() {
//    override fun dispatch(context: CoroutineContext, block: Runnable) {
//        NSRunLoop.mainRunLoop().performBlock {
//            block.run()
//        }
//    }
//}