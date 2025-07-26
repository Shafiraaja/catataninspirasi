package com.aditya.catataninspirasi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aditya.catataninspirasi.data.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteFormDialog(
    note: Note? = null,
    onDismiss: () -> Unit,
    onSave: (judul: String, isi: String, tanggal: String) -> Unit
) {
    var judul by remember { mutableStateOf(note?.judul ?: "") }
    var isi by remember { mutableStateOf(note?.isi ?: "") }
    var tanggal by remember { mutableStateOf(note?.tanggal ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (note == null) "Tambah Catatan" else "Edit Catatan") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = judul,
                    onValueChange = { judul = it },
                    label = { Text("Judul") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = isi,
                    onValueChange = { isi = it },
                    label = { Text("Isi Catatan") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = tanggal,
                    onValueChange = { tanggal = it },
                    label = { Text("Tanggal") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (judul.isNotBlank() && isi.isNotBlank()) {
                    onSave(judul, isi, tanggal)
                }
            }) {
                Text("Simpan")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}
