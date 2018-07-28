package com.thanos.kontribute.ui.group_detail.pay_ins

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Transaction
import com.thanos.kontribute.helper.toNaira
import kotlinx.android.synthetic.main.item_transaction.view.*

class PayInsListAdapter(private var payIns: ArrayList<Transaction>):
        RecyclerView.Adapter<PayInsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(payIns[position])
    }

    override fun getItemCount(): Int = payIns.size

    fun updateTransactions(transactions: ArrayList<Transaction>) {
        payIns = transactions
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(transaction: Transaction) {
            view.txt_group.text = transaction.group
            view.txt_amount.text = transaction.amount.toNaira()
            view.txt_date.text = transaction.date
            view.txt_amount.setTextColor(view.context.resources.getColor(R.color.green))
        }
    }
}
