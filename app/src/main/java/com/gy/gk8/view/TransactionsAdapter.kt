package com.gy.gk8.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gy.gk8.R
import com.gy.gk8.model.Transaction

class TransactionsAdapter(private var transactions: List<Transaction>?) : RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    init {
        updateTransactions(transactions)
    }

    // region Public Methods

    /**
     * Populate the adapter with updated [transactions]
     */
    fun updateTransactions(transactions: List<Transaction>?) {
        this.transactions = transactions

        // Notifying all in order to clear the RV when needed
        notifyDataSetChanged()
    }

    // endregion

    // region RV Adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item, parent, false)

        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        transactions?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount() = transactions?.size ?: 0

    // endregion

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(transaction: Transaction) {
            val resources = itemView.context.resources
            itemView.findViewById<TextView>(R.id.timestamp).text = resources.getString(R.string.timestamp_format, transaction.timeStamp)
            itemView.findViewById<TextView>(R.id.value).text = resources.getString(R.string.value_format, transaction.value)
            itemView.findViewById<TextView>(R.id.from).text = resources.getString(R.string.from_format, transaction.from)
            itemView.findViewById<TextView>(R.id.to).text = resources.getString(R.string.to_format, transaction.to)
            itemView.findViewById<TextView>(R.id.confirmations).text = resources.getString(R.string.confirmations_format, transaction.confirmations)
            itemView.findViewById<TextView>(R.id.hash).text = resources.getString(R.string.hash_format, transaction.hash)
        }
    }
}

