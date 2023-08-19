package com.ningi.poc.ui.composables.elements

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

private sealed class MotionEvent {
    object Idle : MotionEvent()
    data class Down(val position: Offset) : MotionEvent()
    data class Move(val position: Offset) : MotionEvent()
}

@Composable
fun DrawingCanvas(path: Path) {

    var motionEvent by remember { mutableStateOf<MotionEvent>(MotionEvent.Idle) }
    var previousPosition = remember { Offset.Unspecified }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clipToBounds()
            .background(Color.LightGray)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        motionEvent = MotionEvent.Down(it)
                    },
                    onDrag = { change, drag ->
                        motionEvent = MotionEvent.Move(change.position)
                        change.consume()
                    },
                    onDragCancel = { motionEvent = MotionEvent.Idle },
                    onDragEnd = { motionEvent = MotionEvent.Idle }
                )
            }
    ) {
        when (motionEvent) {
            is MotionEvent.Down -> {
                val position = (motionEvent as MotionEvent.Down).position
                path.moveTo(position.x, position.y)
                previousPosition = position
            }

            is MotionEvent.Move -> {
                val position = (motionEvent as MotionEvent.Move).position

                if (previousPosition.isSpecified) {

                    path.quadraticBezierTo(
                        previousPosition.x,
                        previousPosition.y,
                        (previousPosition.x + position.x) / 2,
                        (previousPosition.y + position.y) / 2

                    )
                } else {
                    path.moveTo(position.x, position.y)
                }

                previousPosition = position
            }

            else -> {
                previousPosition = Offset.Unspecified
            }
        }

        drawPath(
            color = Color.Black,
            path = path,
            style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}