package com.example.mytodoapp.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun EditTodoDialog(
    currentTitle: String,
    onEdit: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var todoTitle by remember { mutableStateOf(currentTitle) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Modifier la tâche",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W600) },
        text = {
            OutlinedTextField(
                value = todoTitle,
                onValueChange = { todoTitle = it },
                label = { Text("Nouveau titre") },
                singleLine = true
            )
        },
        confirmButton = {
            Button(onClick = {
                if (todoTitle.isNotEmpty()) {
                    onEdit(todoTitle)
                }
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )) {
                Text("Modifier")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = Color.White
                )) {
                Text("Annuler")
            }
        }
    )
}