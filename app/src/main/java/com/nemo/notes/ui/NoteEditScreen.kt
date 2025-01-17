package com.nemo.notes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val note = remember { mutableStateOf(Note(title = "", content = "")) }

    if (noteId != null) {
        LaunchedEffect(noteId) {
            viewModel.allNotes.collect { notes ->
                notes.find { it.id == noteId }?.let { note.value = it }
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = note.value.title,
            onValueChange = { note.value = note.value.copy(title = it) },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = note.value.content,
            onValueChange = { note.value = note.value.copy(content = it) },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
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