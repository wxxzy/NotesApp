package com.nemo.notes.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nemo.notes.repository.ThemePreference
import com.nemo.notes.ui.theme.NotesAppTheme
import com.nemo.notes.viewmodel.NoteViewModel
import com.nemo.notes.viewmodel.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themePreference = ThemePreference(this)

        setContent {
            // 主题设置 ViewModel
            val themeViewModel: ThemeViewModel = viewModel { ThemeViewModel(themePreference) }
            // 是否为暗色主题
            var isDarkTheme = isSystemInDarkTheme()

            // 主题设置
            NotesAppTheme(themeViewModel) {
                // 使用 Surface 确保整个应用使用正确的背景色
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 创建导航控制器
                    val navController = rememberNavController()
                    val viewModel: NoteViewModel = hiltViewModel()

                    // 设置导航控制器
                    NavHost(navController = navController, startDestination = "noteList") {
                        // 定义 noteList 跳转页
                        composable("noteList") {
                            NoteListScreen(
                                navController, viewModel, isDarkTheme,
                                onThemeChange = {
                                    // 切换主题
                                    isDarkTheme = it
                                    themeViewModel.setThemeMode(
                                        if (it) com.nemo.notes.ui.theme.ThemeMode.DARK else com.nemo.notes.ui.theme.ThemeMode.LIGHT
                                    )
                                }
                            )
                        }
                        // 定义 noteEdit 跳转页
                        composable("noteEdit/{noteId}") { backStackEntry ->
                            val noteId =
                                backStackEntry.arguments?.getString("noteId")?.toLongOrNull()
                            NoteEditScreen(navController, viewModel, noteId)
                        }
                    }

                    // 主题设置页
//                    ThemeSettings(themeViewModel)
                }
            }
        }
    }
}