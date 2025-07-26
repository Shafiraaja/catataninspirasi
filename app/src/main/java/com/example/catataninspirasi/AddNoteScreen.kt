package com.aditya.catataninspirasi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddNoteScreen(
    onSave: (String) -> Unit,
    onCancel: () -> Unit
) {
    var content by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        Text("Catatan Baru")
        Spacer(Modifier.height(8.dp))
        TextField(
            value = content,
            onValueChange = { content = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Row {
            Button(onClick = { onSave(content) }, enabled = content.isNotBlank()) {
                Text("Simpan")
            }
            Spacer(Modifier.width(8.dp))
            OutlinedButton(onClick = onCancel) {
                Text("Batal")
            }
        }
    }
}
