package com.example.mytodoapp.ui.component

import android.graphics.drawable.GradientDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.mytodoapp.data.local.entity.TodoItem
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun TodoItemCard(
    todo: TodoItem,
    onCheckedChange: (Boolean) -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onFavorite: () -> Unit
) {
    var isSwiped by remember { mutableStateOf(false) }
    val swipeState = rememberSwipeableState(initialValue = 0)
    val anchors = mapOf(0f to 0, -200f to -1, 100f to 1)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .swipeable(
                state = swipeState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
    ) {
        // Actions révélées par le swipe
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.5f)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (swipeState.offset.value < -100) {
                // Swipe de droite à gauche : Modifier et Supprimer
                Row(
                    modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Modifier", tint = Color.Blue)
                    }
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "Supprimer", tint = Color.Red)
                    }
                }
            } else if (swipeState.offset.value > 50 && !todo.isCompleted) {
                // Swipe de gauche à droite : Étoile (Favoris)
                IconButton(onClick = {
                    onFavorite()
                    isSwiped = !isSwiped
                    //swipeState.animateTo(0) // Referme automatiquement le swipe
                }) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Favoris",
                        tint = Color.White
                    )
                }
            }
        }

        // Contenu principal
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            ) {
            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red,
                    uncheckedColor = Color.Black,
                    checkmarkColor = Color.White
                ),
                modifier = Modifier.padding(bottom = 10.dp)
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
                modifier = Modifier.weight(1f).padding(bottom = 10.dp)
            )
            if (todo.isFavorite) {
                IconButton(
                    onClick = onFavorite,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Favoris",
                        tint = Color.DarkGray
                    )
                }
            }
        }
    }
}