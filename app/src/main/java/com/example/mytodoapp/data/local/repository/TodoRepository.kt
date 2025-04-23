package com.example.mytodoapp.data.local.repository

import com.example.mytodoapp.data.local.dao.TodoDao
import com.example.mytodoapp.data.local.entity.TodoItem
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {

    suspend fun getAllTodos(): List<TodoItem> = todoDao.getAllTodos()

    suspend fun getTodoById(id: String): TodoItem = todoDao.getTodo(id)

    suspend fun insertTodo(todo: TodoItem) = todoDao.insertTodo(todo)

    suspend fun updateTodo(todo: TodoItem) = todoDao.updateTodo(todo)

    suspend fun deleteTodo(todo: TodoItem) = todoDao.deleteTodo(todo)
}