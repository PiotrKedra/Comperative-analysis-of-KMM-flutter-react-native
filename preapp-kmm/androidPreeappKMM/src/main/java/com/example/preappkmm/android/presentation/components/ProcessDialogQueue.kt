package com.example.preappkmm.android.presentation.components

import androidx.compose.runtime.Composable
import com.example.preappkmm.domain.util.Queue

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<String>?
) {
    dialogQueue?.peek()?.let{ message ->
        GenericDialog(title = "Error", description = message)
    }
}