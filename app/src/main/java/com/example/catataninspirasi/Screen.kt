package com.aditya.catataninspirasi

sealed class Screen(val route: String, val title: String) {
    object Beranda : Screen("beranda", "Beranda")
    object Catatan : Screen("catatan", "Catatan")
    object Panduan : Screen("panduan", "Panduan")
    object Tentang : Screen("tentang", "Tentang")
}
