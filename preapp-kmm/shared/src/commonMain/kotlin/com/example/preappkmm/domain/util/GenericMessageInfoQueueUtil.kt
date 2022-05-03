package com.example.preappkmm.domain.util

import com.example.preappkmm.domain.model.GenericMessageInfo

// kkm cannot use extension function in swift UI
class GenericMessageInfoQueueUtil {

    fun doesMessageAlreadyExistInQueue(
        queue: Queue<GenericMessageInfo>,
        messageInfo: GenericMessageInfo
    ): Boolean {
        for (item in queue.items) {
            if (item.id == messageInfo.id) {
                return true
            }
        }
        return false
    }
}