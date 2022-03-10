package com.gy.gk8.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gy.gk8.model.Transaction
import com.gy.gk8.model.network.TransactionApi
import java.lang.Exception

const val TAG = "TransactionsVM"

class TransactionsViewModel : ViewModel() {

    // region Members

    // Saving the transactions locally to support the likes of configuration changes
    var transactions: List<Transaction>? = null

    // endregion

    // region Public Methods

    /**
     * Find all transactions containing [address]
     */
    suspend fun getTransactionsForAddress(address: String) : List<Transaction>? {
        try {
            val transactionsRes = TransactionApi.retrofitService.getTransactions(address)
            transactions = transactionsRes.result

            Log.d(TAG, "result status: ${transactionsRes.message}, count: ${transactionsRes.result.size}")
        }
        catch (e: Exception) {
            Log.e(TAG, "failed to get transactions")
            e.printStackTrace()
        }

        return transactions
    }

    // endregion
    
}