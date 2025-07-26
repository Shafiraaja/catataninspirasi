package com.aditya.catataninspirasi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TentangScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("ℹ️ Tentang Aplikasi", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("📌 Nama: Catatan Inspirasi")
        Text("🛠️ Versi: 1.0")
        Text("👨‍💻 Pengembang: Shafira")
        Text("🎯 Desain: Nuansa Indonesia, profesional, dan ringan digunakan.")
    }
}
