package com.thanos.kontribute.ui.transactions

import com.thanos.kontribute.data.model.Group
import com.thanos.kontribute.data.model.Transaction
import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface TransactionsContract {

    interface TransactionsPresenter: BasePresenter<TransactionsView> {
        fun fetchTransactions()
    }

    interface TransactionsView: BaseView {
        fun showProgress()
        fun hideProgress()
        fun showTransactions(transactions: ArrayList<Transaction>)
    }
}