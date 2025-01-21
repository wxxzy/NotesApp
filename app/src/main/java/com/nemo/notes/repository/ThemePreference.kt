package com.nemo.notes.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nemo.notes.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 创建 ThemePreference 类, 用于管理主题设置
private val Context.dataStore by preferencesDataStore(name = "theme_settings")

/**
 * 主题设置类, 用于管理主题设置,
 * 包括获取和设置主题模式, 初始化主题设置
 *
 * @property context Context
 * @constructor 创建 ThemePreference 实例
 */
class ThemePreference(private val context: Context) {
    // 创建主题设置的 key, 用于存储主题设置
    private val themeKey = stringPreferencesKey("theme_mode")

    // 获取主题模式，默认返回 SYSTEM
    val themeMode: Flow<ThemeMode> = context.dataStore.data.map { preferences ->
        try {
            preferences[themeKey]?.let { ThemeMode.valueOf(it) } ?: ThemeMode.SYSTEM
        } catch (e: Exception) {
            ThemeMode.SYSTEM
        }
    }

    // 设置主题模式
    suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[themeKey] = mode.name
        }
    }

    // 添加初始化方法，如果没有设置则默认设置为 SYSTEM
    suspend fun initializeTheme() {
        context.dataStore.edit { preferences ->
            if (!preferences.contains(themeKey)) {
                preferences[themeKey] = ThemeMode.SYSTEM.name
            }
        }
    }
}
