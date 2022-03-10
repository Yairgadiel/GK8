package com.gy.gk8.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gy.gk8.R
import com.gy.gk8.databinding.ActivityTransactionsBinding
import com.gy.gk8.model.Transaction
import com.gy.gk8.viewModel.TransactionsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionsBinding
    private lateinit var transactionsViewModel: TransactionsViewModel
    private lateinit var transactionsAdapter: TransactionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transactionsViewModel = ViewModelProvider(this).get(TransactionsViewModel::class.java)

        // Initialize the transactions adapter with the values stored in the VM.
        // This is done in order to support configuration changes
        transactionsAdapter = TransactionsAdapter(transactionsViewModel.transactions)

        // Initialize the transactions RecyclerView
        binding.transactions.apply {
            adapter = transactionsAdapter
            layoutManager = LinearLayoutManager(this@TransactionsActivity)
        }

        binding.searchBtn.setOnClickListener {
            // Show loader
            binding.loader.visibility = View.VISIBLE

            lifecycleScope.launch(Dispatchers.Default) {
                updateTransactions(transactionsViewModel.getTransactionsForAddress(binding.addressInput.text.toString()))
            }
        }
    }

    /**
     * Update the UI with the given [transactions]
     */
    private fun updateTransactions(transactions: List<Transaction>?) {
        lifecycleScope.launch(Dispatchers.Main) {
            transactionsAdapter.updateTransactions(transactions)

            // If no transactions were received - let the user know
            if (transactions == null || transactions.isEmpty()) {
                Toast.makeText(this@TransactionsActivity, getString(R.string.no_transactions_msg), Toast.LENGTH_SHORT).show()
            }

            // Hide loader
            binding.loader.visibility = View.INVISIBLE
        }
    }
}