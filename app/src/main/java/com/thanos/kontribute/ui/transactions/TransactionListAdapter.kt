package com.thanos.kontribute.ui.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Transaction
import com.thanos.kontribute.helper.toNaira
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionListAdapter(private var transactionList: ArrayList<Transaction>,
                             private var transactionListListener: TransactionListListener):
        RecyclerView.Adapter<TransactionListAdapter.TransactionListViewHolder>() {

    interface TransactionListListener {
        fun onTransactionSelected(transaction: Transaction)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionListViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        return TransactionListViewHolder(view)
    }

    override fun getItemCount(): Int = transactionList.size

    override fun onBindViewHolder(holder: TransactionListViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
    }

    fun updateTransactions(transactions: ArrayList<Transaction>) {
        this.transactionList = transactions
        notifyDataSetChanged()
    }

    inner class TransactionListViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            transactionListListener.onTransactionSelected(transactionList[adapterPosition])
        }

        fun bind(transaction: Transaction) {
            itemView.txt_group.text = transaction.group
            itemView.txt_amount.text = transaction.amount.toNaira()
            itemView.txt_date.text = transaction.date
            if (transaction.withdrawal) {
                itemView.txt_amount.setTextColor(itemView.context.resources.getColor(R.color.green))
            }

        }

    }
}