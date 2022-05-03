package com.example.preappkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.example.preappkmm.domain.model.GenericMessageInfo
import com.example.preappkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericMessageInfo>?,
    onRemoveHeadMessageFromQueue: () -> Unit,
) {
    dialogQueue?.peek()?.let{ dialogInfo ->
        GenericDialog(
            onDismiss = dialogInfo.onDismiss,
            title = dialogInfo.title,
            description = dialogInfo.description,
            positiveAction = dialogInfo.positiveAction,
            negativeAction = dialogInfo.negativeAction,
            onRemoveHeadFromQueue = onRemoveHeadMessageFromQueue
        )
    }
}