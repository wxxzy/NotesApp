package com.nemo.notes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
fun NoteListScreen(navController: NavHostController, viewModel: NoteViewModel) {
    val viewModel: NoteViewModel = hiltViewModel()
    val notes by viewModel.allNotes.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "My Notes", style = MaterialTheme.typography.headlineMedium)
        LazyColumn {
            items(notes) { note ->
                Card(
                    onClick = { navController.navigate("noteEdit/${note.id}") },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("noteEdit/null") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add New Note")
        }
    }
}
