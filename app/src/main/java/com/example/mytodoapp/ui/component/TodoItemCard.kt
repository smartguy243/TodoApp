package com.example.mytodoapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytodoapp.data.local.entity.TodoItem

@Composable
fun TodoItemCard(
    todo: TodoItem,
    onCheckedChange: (Boolean) -> Unit
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
            onCheckedChange = onCheckedChange
        )

        Text(
            text = todo.title,
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = if (todo.isCompleted) TextDecoration.LineThrough else null,
            ),
        )
    }
}