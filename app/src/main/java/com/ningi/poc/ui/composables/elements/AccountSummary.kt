package com.ningi.poc.ui.composables.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ningi.poc.ui.theme.NegativeColor
import com.ningi.poc.ui.theme.PositiveColor
import java.text.NumberFormat

@Composable
fun AccountSummary(value: Double, name: String, imageVector: ImageVector) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(imageVector, contentDescription = name)
        Column(modifier = Modifier.weight(1f)) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = NumberFormat.getCurrencyInstance().format(value)
            )
            Text(text = name)
        }
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(if (value < 0.0) NegativeColor else PositiveColor)
        )
    }
}