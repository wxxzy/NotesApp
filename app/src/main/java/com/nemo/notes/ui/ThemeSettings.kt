package com.nemo.notes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nemo.notes.ui.theme.ThemeMode
import com.nemo.notes.viewmodel.ThemeViewModel

@Composable
fun ThemeSettings(
    themeViewModel: ThemeViewModel,
    modifier: Modifier = Modifier
) {
    val currentTheme by themeViewModel.themeMode.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "主题设置",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ThemeOption(
            title = "跟随系统",
            selected = currentTheme == ThemeMode.SYSTEM,
            onClick = { themeViewModel.setThemeMode(ThemeMode.SYSTEM) }
        )

        ThemeOption(
            title = "浅色模式",
            selected = currentTheme == ThemeMode.LIGHT,
            onClick = { themeViewModel.setThemeMode(ThemeMode.LIGHT) }
        )

        ThemeOption(
            title = "深色模式",
            selected = currentTheme == ThemeMode.DARK,
            onClick = { themeViewModel.setThemeMode(ThemeMode.DARK) }
        )
    }
}

@Composable
private fun ThemeOption(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    RadioButton(
        selected = selected,
        onClick = onClick,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(start = 8.dp)
    )
}