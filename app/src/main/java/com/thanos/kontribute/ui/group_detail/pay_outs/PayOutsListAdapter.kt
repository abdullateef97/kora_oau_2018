package com.thanos.kontribute.ui.group_detail.pay_outs

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Transaction
import com.thanos.kontribute.helper.toNaira
import kotlinx.android.synthetic.main.item_transaction.view.*

class PayOutsListAdapter(private var payOuts: ArrayList<Transaction>):
        RecyclerView.Adapter<PayOutsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(payOuts[position])
    }

    override fun getItemCount(): Int = payOuts.size
    fun updateTransactions(transactions: ArrayList<Transaction>) {
        payOuts = transactions
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(transaction: Transaction) {
            view.txt_group.text = transaction.group
            view.txt_amount.text = transaction.amount.toNaira()
            view.txt_date.text = transaction.date
        }
    }
}
