package com.aditya.catataninspirasi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BerandaScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("👋 Selamat datang di Catatan Inspirasi!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Aplikasi ini membantu kamu menyimpan catatan harian, ide, dan inspirasi dengan mudah.")
    }
}
