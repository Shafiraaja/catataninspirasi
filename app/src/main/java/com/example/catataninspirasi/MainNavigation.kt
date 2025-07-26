package com.aditya.catataninspirasi.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.aditya.catataninspirasi.Screen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val screens = listOf(
        Screen.Beranda to Icons.Default.Home,
        Screen.Catatan to Icons.Default.List,
        Screen.Panduan to Icons.Default.MenuBook,
        Screen.Tentang to Icons.Default.Info
    )

    var selected by remember { mutableStateOf(Screen.Beranda.route) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEach { (screen, icon) ->
                    NavigationBarItem(
                        selected = selected == screen.route,
                        onClick = {
                            selected = screen.route
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(icon, contentDescription = screen.title) },
                        label = { Text(screen.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Beranda.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Beranda.route) { BerandaScreen() }
            composable(Screen.Catatan.route) { NoteListScreen() }
            composable(Screen.Panduan.route) { PanduanScreen() }
            composable(Screen.Tentang.route) { TentangScreen() }
        }
    }
}
