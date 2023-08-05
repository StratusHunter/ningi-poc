package com.ningi.poc.ui.composables.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ningi.poc.R
import com.ningi.poc.ui.theme.NegativeColor
import com.ningi.poc.ui.theme.PositiveColor
import java.text.NumberFormat
import kotlin.math.absoluteValue

@Composable
fun AccountTotalSummary(totalPositive: Double, totalNegative: Double) {

    val net = totalPositive + totalNegative
    val iOwe = totalNegative.absoluteValue
    val accTotal = totalPositive + iOwe
    val currencyFormatter = NumberFormat.getCurrencyInstance()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PieChart(
            modifier = Modifier
                .fillMaxWidth(0.25f)
                .aspectRatio(1f),
            entries = listOf(
                (totalPositive / accTotal).toFloat() to PositiveColor,
                (totalNegative / accTotal).toFloat() to NegativeColor,
            )
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(id = R.string.net_worth, currencyFormatter.format(net)),
                fontWeight = FontWeight.Bold
            )
            Text(
                color = PositiveColor,
                text = stringResource(id = R.string.i_own, currencyFormatter.format(totalPositive))
            )
            Text(
                color = NegativeColor,
                text = stringResource(id = R.string.i_owe, currencyFormatter.format(iOwe))
            )
        }
    }
}