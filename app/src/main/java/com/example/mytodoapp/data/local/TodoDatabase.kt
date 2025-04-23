package com.example.mytodoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytodoapp.data.local.dao.TodoDao
import com.example.mytodoapp.data.local.entity.TodoItem

@Database(entities = [TodoItem::class], version = 2, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}