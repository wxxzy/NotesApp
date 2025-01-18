package com.nemo.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemo.notes.model.Note
import com.nemo.notes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val allNotes: Flow<List<Note>> = repository.getAllNotes()

    // 新增搜索字段
    private val searchQuery = MutableStateFlow("")

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun delete(noteId: Long) = viewModelScope.launch {
        repository.delete(noteId)
    }

    // 新增搜索方法
    val filteredNotes: Flow<List<Note>> = combine(allNotes, searchQuery) { notes, query ->
        query to notes
    }.flatMapLatest { (query, notes) ->
        if (query.isEmpty()) {
            flowOf(notes)
        } else {
            repository.searchNotes(query)
        }
    }

    // 新增搜索方法
    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }
}