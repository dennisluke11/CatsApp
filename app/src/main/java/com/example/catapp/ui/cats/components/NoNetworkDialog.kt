package com.example.catapp.ui.cats.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.catapp.R

@Composable
fun NoNetworkDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(id = R.string.ok))
                }
            },
            title = { Text(stringResource(id = R.string.no_internet_connection)) },
            text = {
                Text(stringResource(id = R.string.offline_mode_message))
            }
        )
    }
}