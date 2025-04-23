package com.example.mytodoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodoapp.data.local.entity.TodoItem
import com.example.mytodoapp.data.local.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository) :
    ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos

    fun getAllTodos() {
        viewModelScope.launch {
            _todos.value = todoRepository.getAllTodos()
        }
    }

    fun addTodo(todo: TodoItem) {
        viewModelScope.launch {
            todoRepository.insertTodo(todo)
            getAllTodos()
        }
    }

    fun updateTodo(todo: TodoItem) {
        viewModelScope.launch {
            todoRepository.updateTodo(todo)
            getAllTodos()
        }
    }

    fun deleteTodo(todo: TodoItem) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
            getAllTodos()
        }
    }
}