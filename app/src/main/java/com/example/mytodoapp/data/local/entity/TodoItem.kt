package com.example.mytodoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class TodoItem(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var isCompleted: Boolean = false,
    var isFavorite: Boolean = false
)