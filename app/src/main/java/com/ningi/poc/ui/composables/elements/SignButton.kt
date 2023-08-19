package com.ningi.poc.ui.composables.elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ningi.poc.R

@Composable
fun SignButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(modifier = modifier, onClick = onClick) {

        val needsSigning = stringResource(id = R.string.needs_signing)
        Icon(Icons.Filled.Edit, contentDescription = needsSigning)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = needsSigning)
    }
}