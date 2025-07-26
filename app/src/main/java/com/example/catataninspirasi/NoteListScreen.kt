package com.aditya.catataninspirasi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.aditya.catataninspirasi.data.Note
import com.aditya.catataninspirasi.data.NoteDatabase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen() {
    val context = LocalContext.current
    val dao = remember { NoteDatabase.getInstance(context).noteDao() }
    val scope = rememberCoroutineScope()

    var allNotes by remember { mutableStateOf(listOf<Note>()) }
    var filteredNotes by remember { mutableStateOf(listOf<Note>()) }
    var query by remember { mutableStateOf(TextFieldValue("")) }

    var showForm by remember { mutableStateOf(false) }
    var editingNote by remember { mutableStateOf<Note?>(null) }
    var confirmDelete by remember { mutableStateOf<Note?>(null) }

    LaunchedEffect(true) {
        dao.getAll().collect {
            allNotes = it
            filteredNotes = filterNotes(it, query.text)
        }
    }

    LaunchedEffect(query.text) {
        filteredNotes = filterNotes(allNotes, query.text)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📘 Catatan Inspirasi") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    editingNote = null
                    showForm = true
                },
                containerColor = Color(0xFF4CAF50)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Catatan")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 🔍 Search Bar
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Cari catatan...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            LazyColumn {
                items(filteredNotes) { note ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF8E1)
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(note.judul, style = MaterialTheme.typography.titleLarge)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("📅 ${note.tanggal}", color = Color.Gray)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(note.isi)

                            Row(modifier = Modifier.padding(top = 12.dp)) {
                                IconButton(onClick = {
                                    editingNote = note
                                    showForm = true
                                }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Edit")
                                }
                                IconButton(onClick = {
                                    confirmDelete = note
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Hapus")
                                }
                            }
                        }
                    }
                }
            }
        }

        // Dialog Tambah/Edit
        if (showForm) {
            NoteFormDialog(
                note = editingNote,
                onDismiss = { showForm = false },
                onSave = { judul, isi, tanggal ->
                    scope.launch {
                        if (editingNote == null) {
                            dao.insert(Note(judul = judul, isi = isi, tanggal = tanggal))
                        } else {
                            dao.update(editingNote!!.copy(judul = judul, isi = isi, tanggal = tanggal))
                        }
                        showForm = false
                    }
                }
            )
        }

        // Dialog Konfirmasi Hapus
        if (confirmDelete != null) {
            AlertDialog(
                onDismissRequest = { confirmDelete = null },
                title = { Text("Hapus Catatan") },
                text = { Text("Yakin ingin menghapus catatan ini?") },
                confirmButton = {
                    TextButton(onClick = {
                        scope.launch {
                            dao.delete(confirmDelete!!)
                            confirmDelete = null
                        }
                    }) {
                        Text("Hapus")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { confirmDelete = null }) {
                        Text("Batal")
                    }
                }
            )
        }
    }
}

// Fungsi untuk memfilter berdasarkan kata kunci pencarian
private fun filterNotes(notes: List<Note>, query: String): List<Note> {
    return if (query.isBlank()) notes
    else notes.filter {
        it.judul.contains(query, ignoreCase = true) ||
                it.isi.contains(query, ignoreCase = true) ||
                it.tanggal.contains(query, ignoreCase = true)
    }
}
