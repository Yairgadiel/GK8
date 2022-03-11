package com.gy.gk8.model.network

import com.gy.gk8.model.Transaction
import com.gy.gk8.model.TransactionsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.etherscan.io/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TransactionApiService {
    /**
     * Returns a [List] of [Transaction] and this method can be called from a Coroutine.
     * Sample address "0xddbd2b932c763ba5b1b7ae3b362eac3e8d40121a"
     */
    @GET("api?module=account&action=txlist&endblock=99999999&sort=asc&apikey=N35IIK56F4S17K9JKYPEJG17YZD413RB24&startblock=0")
    suspend fun getTransactions(@Query("address") address : String ) : TransactionsResponse
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object TransactionApi {
    val retrofitService: TransactionApiService by lazy {
        retrofit.create(TransactionApiService::class.java)
    }
}