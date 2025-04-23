package com.example.mytodoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mytodoapp.data.local.entity.TodoItem

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoItem")
    suspend fun getAllTodos(): List<TodoItem>

    @Query("SELECT * FROM TodoItem WHERE id = :id")
    suspend fun getTodo(id: String): TodoItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoItem)

    @Update
    suspend fun updateTodo(todo: TodoItem)

    @Delete
    suspend fun deleteTodo(todo: TodoItem)
}