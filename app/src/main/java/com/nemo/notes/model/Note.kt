// Note.kt
package com.nemo.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    val tags: List<String> = emptyList(),  // 新增标签字段
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)