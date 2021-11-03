package com.example.jetpackcomposetest.models

data class Account(
    val balance: String = "£24.24",
    val currency: String = "British Pounds",
    val mainAccount: String = "Main account",
    val percent: String = "23%"
)