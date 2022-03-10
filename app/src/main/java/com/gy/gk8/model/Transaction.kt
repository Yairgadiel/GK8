package com.gy.gk8.model

import com.squareup.moshi.Json

data class Transaction(
    @field:Json(name="blockNumber") val blockNumber: Int,
    @field:Json(name="timeStamp") val timeStamp: Int,
    @field:Json(name="hash") val hash: String,
    @field:Json(name="nonce") val nonce: String,
    @field:Json(name="blockHash") val blockHash: String,
    @field:Json(name="transactionIndex") val transactionIndex: Int,
    @field:Json(name="from") val from: String,
    @field:Json(name="to") val to: String,
    @field:Json(name="value") val value: String,
    @field:Json(name="gas") val gas: Int,
    @field:Json(name="gasPrice") val gasPrice: String,
    @field:Json(name="isError") val isError: Int,
    @field:Json(name="txreceipt_status") val txreceipt_status: String,
    @field:Json(name="input") val input: String,
    @field:Json(name="contractAddress") val contractAddress: String,
    @field:Json(name="cumulativeGasUsed") val cumulativeGasUsed: Int,
    @field:Json(name="gasUsed") val gasUsed: Int,
    @field:Json(name="confirmations") val confirmations: Int,
)