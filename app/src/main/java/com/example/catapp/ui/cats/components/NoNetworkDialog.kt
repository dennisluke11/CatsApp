package com.example.catapp.ui.cats.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

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
                    Text("OK")
                }
            },
            title = { Text("No Internet Connection") },
            text = {
                Text("You're using this app in offline mode. Please enable mobile data or Wi-Fi to experience smooth image loading.")
            }
        )
    }
}