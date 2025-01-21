package com.nemo.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemo.notes.repository.ThemePreference
import com.nemo.notes.ui.theme.ThemeMode
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val themePreference: ThemePreference
) : ViewModel() {

    init {
        // 在 ViewModel 初始化时确保主题被初始化
        viewModelScope.launch {
            themePreference.initializeTheme()
        }
    }

    val themeMode: StateFlow<ThemeMode> = themePreference.themeMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ThemeMode.SYSTEM  // 设置初始值为 SYSTEM
        )

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            themePreference.setThemeMode(mode)
        }
    }
}