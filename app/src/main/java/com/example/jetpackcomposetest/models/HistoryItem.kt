package com.example.jetpackcomposetest.models

sealed class HistoryItem {
    class TITLE(var isToday: Boolean, var date: String = "22.06.2021"): HistoryItem()
    class PAYMENT(var name: String = "Elon Musk", var toAccount: String = "GBP", var amount: Double = 15.00, var localDateTime: String = "22.06.2021", var accepted: Boolean, var incoming: Boolean) : HistoryItem()
}

