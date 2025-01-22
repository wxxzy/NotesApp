package com.nemo.notes.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nemo.notes.viewmodel.NoteViewModel

/**
 * 笔记列表界面
 * @param navController 导航控制器
 * @param viewModel 笔记ViewModel
 * @param isDarkTheme 是否为暗色主题
 * @param onThemeChange 切换主题回调
 *
 */
@Composable
fun NoteListScreen(
    navController: NavHostController,
    viewModel: NoteViewModel,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    // 收集所有笔记的状态
    val notes by viewModel.filteredNotes.collectAsState(initial = emptyList())
    // 新增搜索字段
    var searchQuery by remember { mutableStateOf("") }

    // 添加动画显示状态
    var isVisible by remember { mutableStateOf(false) }
    // 启动时触发动画
    LaunchedEffect(Unit) {
        isVisible = true
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // 搜索框
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.setSearchQuery(it)
            },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth().animateContentSize(
                animationSpec = spring(
                    dampingRatio = 0.8f,
                    stiffness = 400f
                )
            )  // 搜索框添加大小变化动画
        )
        // 添加间距
        Spacer(modifier = Modifier.height(16.dp))
        // 标题栏添加淡入动画
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 显示标题
                Text(text = "My Notes", style = MaterialTheme.typography.headlineMedium)
                // 添加切换主题按钮
                IconButton(onClick = { onThemeChange(!isDarkTheme) }) {
                    Icon(
                        // 根据主题显示不同图标
                        imageVector = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                        contentDescription = "Toggle Theme"
                    )
                }
            }
        }
        // 显示笔记列表,笔记列表添加动画
        LazyColumn {
            items(notes, key = { it.id }) { note ->
                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn(
                        animationSpec = tween(500)
                    ) + expandVertically(
                        animationSpec = tween(500)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(300)
                    ) + shrinkVertically(
                        animationSpec = tween(300)
                    )
                ) {
                    Card(
                        onClick = { navController.navigate("noteEdit/${note.id}") },
                        modifier = Modifier
                            .padding(8.dp)
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = 0.8f,
                                    stiffness = 400f
                                )
                            )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = note.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = note.content,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            if (note.tags.isNotEmpty()) {
                                Text(
                                    text = "Tags: ${note.tags.joinToString(", ")}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
        // 添加间距
        Spacer(modifier = Modifier.height(16.dp))
        // 添加新笔记按钮,添加按钮的淡入动画
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            Button(
                onClick = { navController.navigate("noteEdit/null") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add New Note")
            }
        }
    }
}