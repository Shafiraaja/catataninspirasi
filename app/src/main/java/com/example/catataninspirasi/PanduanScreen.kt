package com.aditya.catataninspirasi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PanduanScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("📘 Panduan Penggunaan", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("1. Klik tombol ➕ untuk menambah catatan baru.")
        Text("2. Klik ✏️ untuk mengedit catatan.")
        Text("3. Klik 🗑️ untuk menghapus catatan.")
        Text("4. Catatan disimpan otomatis di perangkat kamu.")
    }
}
