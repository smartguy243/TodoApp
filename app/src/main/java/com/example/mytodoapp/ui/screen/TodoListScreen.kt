package com.example.mytodoapp.ui.screen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytodoapp.data.local.entity.TodoItem
import com.example.mytodoapp.ui.component.AddTodoDialog
import com.example.mytodoapp.ui.component.DeleteConfirmationDialog
import com.example.mytodoapp.ui.component.EditTodoDialog
import com.example.mytodoapp.ui.component.TodoItemCard
import com.example.mytodoapp.viewModel.TodoViewModel

@Composable
fun TodoListScreen(
    viewModel: TodoViewModel = hiltViewModel()
) {
    val todos by viewModel.todos.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var editDialogVisible by remember { mutableStateOf(false) }
    var deleteDialogVisible by remember { mutableStateOf(false) }
    var selectedTodo by remember { mutableStateOf<TodoItem?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getAllTodos()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter un Todo")
            }
        }

    ) { padding ->
        if (todos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No tasks",
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                items(todos) { todo ->

                    Spacer(modifier = Modifier.height(10.dp))

                    TodoItemCard(
                        todo = todo,
                        onCheckedChange = { isChecked ->
                            viewModel.updateTodo(todo.copy(isCompleted = isChecked))
                        }
                    )
                }
            }
        }

        if (showDialog) {
            AddTodoDialog(
                onAdd = { title ->
                    viewModel.addTodo(TodoItem(title = title))
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }

        if (editDialogVisible && selectedTodo != null) {
            EditTodoDialog(
                currentTitle = selectedTodo!!.title,
                onEdit = { newTitle ->
                    viewModel.updateTodo(selectedTodo!!.copy(title = newTitle))
                    editDialogVisible = false
                },
                onDismiss = { editDialogVisible = false }
            )
        }

        if (deleteDialogVisible && selectedTodo != null) {
            DeleteConfirmationDialog(
                onConfirm = {
                    viewModel.deleteTodo(selectedTodo!!)
                    deleteDialogVisible = false
                },
                onDismiss = { deleteDialogVisible = false }
            )
        }
    }
}