package com.thanos.kontribute.ui.group_detail.pay_outs

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanos.kontribute.R


class PayOutsListAdapter : RecyclerView.Adapter<PayOutsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 1

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
    }
}
