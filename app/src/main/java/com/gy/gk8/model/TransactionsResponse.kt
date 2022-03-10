package com.gy.gk8.model

import com.squareup.moshi.Json

data class TransactionsResponse(
    @field:Json(name="status") val status: Int,
    @field:Json(name="message") val message: String,
    @field:Json(name="result") val result: List<Transaction>,
)
