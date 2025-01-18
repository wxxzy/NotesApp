package com.nemo.notes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nemo.notes.model.Note
import com.nemo.notes.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditScreen(
    navController: NavController,
    viewModel: NoteViewModel,
    noteId: Long?
) {
    // 使用 remember 保存笔记状态
    val note = remember { mutableStateOf(Note(title = "", content = "")) }
    // 新增标签字段
    var newTag by remember { mutableStateOf("") }

    // 如果 noteId 不为空，加载对应的笔记
    if (noteId != null) {
        LaunchedEffect(noteId) {
            viewModel.allNotes.collect { notes ->
                notes.find { it.id == noteId }?.let { note.value = it }
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // 标题输入框
        TextField(
            value = note.value.title,
            onValueChange = { note.value = note.value.copy(title = it) },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // 内容输入框
        TextField(
            value = note.value.content,
            onValueChange = { note.value = note.value.copy(content = it) },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // 新增标签输入框
        Text(text = "Tags: ${note.value.tags.joinToString(", ")}", style = MaterialTheme.typography.bodySmall)
        Row(modifier = Modifier.fillMaxWidth()) {
            // 输入标签
            TextField(
                value = newTag,
                onValueChange = { newTag = it },
                label = { Text("Add Tag") },
                modifier = Modifier.weight(1f)
            )
            // 添加标签按钮
            Button(
                onClick = {
                    if (newTag.isNotBlank()) {
                        note.value = note.value.copy(tags = note.value.tags + newTag)
                        newTag = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // 保存按钮
        Button(
            onClick = {
                if (noteId == null) {
                    viewModel.insert(note.value)
                } else {
                    viewModel.update(note.value.copy(id = noteId))
                }
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (noteId == null) "Create Note" else "Update Note")
        }
    }
}