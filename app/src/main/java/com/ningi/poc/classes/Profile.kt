package com.ningi.poc.classes

data class Profile(
    val name: String,
    val image: String,
    val accounts: List<Account>
)