package com.ningi.poc.ui.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ningi.poc.classes.Account
import com.ningi.poc.classes.Profile
import com.ningi.poc.ui.composables.elements.AccountSummary
import com.ningi.poc.ui.composables.elements.AccountTotalSummary
import com.ningi.poc.ui.composables.elements.ProfileHeader
import com.ningi.poc.viewmodels.AccountViewModel

@Composable
fun ProfileScreen(
    viewModel: AccountViewModel = viewModel {
        AccountViewModel(
            profile = Profile(
                name = "Peter",
                image = "https://ningi.co.uk/_next/image?url=%2Fningi%2Fteam_pictures%2Fpete_profile.png&w=128&q=75",
                accounts = listOf(
                    Account(name = "Pension", value = 60_000.0, icon = Icons.Filled.Lock),
                    Account(name = "Savings Account", value = 10_000.0, icon = Icons.Filled.DateRange),
                    Account(name = "Current Account", value = 1_000.0, icon = Icons.Filled.ShoppingCart),
                    Account(name = "Mortgage", value = -160_000.0, icon = Icons.Filled.Home)
                ),
            )
        )
    }
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            ProfileHeader(
                name = viewModel.profile.name,
                image = viewModel.profile.image,
            )
        }
        item {
            Divider()
        }
        val items = viewModel.profile.accounts
        if (items.isNotEmpty()) {

            item {
                AccountTotalSummary(
                    totalPositive = items.filter { it.value >= 0.0 }.sumOf { it.value },
                    totalNegative = items.filter { it.value < 0.0 }.sumOf { it.value }
                )
            }
        }
        items(items = items) {
            AccountSummary(name = it.name, value = it.value, imageVector = it.icon)
        }
    }
}