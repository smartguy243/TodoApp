package com.example.mytodoapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytodoapp.data.local.entity.TodoItem

@Composable
fun TodoItemRow(
    todo: TodoItem,
    onCheckedChange: (Boolean) -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(5.dp))

        Checkbox(
            checked = todo.isCompleted,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Black,
                checkmarkColor = Color.White)
        )

        Text(
            text = todo.title,
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else null,
                color = if (todo.isCompleted) Color.Red else Color.Black
            ),
            fontStyle = if (todo.isCompleted) FontStyle.Italic else null,
            fontWeight = if (todo.isCompleted) FontWeight.W300 else null,
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = onEdit) {
            Icon(Icons.Default.Edit, contentDescription = "Modifier")
        }

        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Supprimer")
        }
    }
}