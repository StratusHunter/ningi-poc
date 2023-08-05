package com.ningi.poc.ui.composables.elements

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun PieChart(modifier: Modifier = Modifier, entries: List<Pair<Float, Color>>) {
    Canvas(modifier = modifier) {
        val startAngles = entries.map { it.first }.fold(emptyList<Float>()) { acc, _ -> acc + (acc.sum() * 360f) }
        entries.forEachIndexed { index, entry ->
            drawArc(
                color = entry.second,
                startAngle = startAngles[index],
                sweepAngle = entry.first * 360f,
                useCenter = true,
                topLeft = Offset.Zero,
                size = size
            )
        }
    }
}