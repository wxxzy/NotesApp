package com.nemo.notes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nemo.notes.viewmodel.NoteViewModel

@Composable
fun NoteListScreen(navController: NavHostController) {
    val viewModel: NoteViewModel = hiltViewModel()
    val notes by viewModel.allNotes.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "My Notes", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(notes) { note ->
                Text(text = note.title, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun NoteEditScreen(navController: NavHostController, noteId: Long?) {
    // 你的组合内容
}