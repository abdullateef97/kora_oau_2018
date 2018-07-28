package com.thanos.kontribute.ui.transactions

import com.thanos.kontribute.data.model.Transaction

class TransactionsPresenter : TransactionsContract.TransactionsPresenter {

    private var transactionsView: TransactionsContract.TransactionsView? = null

    override fun attachView(view: TransactionsContract.TransactionsView) {
        this.transactionsView = view
    }

    override fun fetchTransactions() {
        transactionsView?.showProgress()
        val transactions = arrayListOf(
                Transaction(amount = 90000, group = "Main One", date = "25/04/2018", deposit = true, withdrawal = false),
                Transaction(amount = 90000, group = "Germany Straight", date = "22/03/2018", deposit = true, withdrawal = false),
                Transaction(amount = 4500, group = "Main One", date = "28/01/2018", deposit = true, withdrawal = false),
                Transaction(amount = 30000, group = "Ajo", date = "12/12/2017", deposit = false, withdrawal = true),
                Transaction(amount = 90000, group = "Main One", date = "25/04/2018", deposit = true, withdrawal = false),
                Transaction(amount = 90000, group = "Germany Straight", date = "22/03/2018", deposit = true, withdrawal = false),
                Transaction(amount = 4500, group = "Main One", date = "28/01/2018", deposit = true, withdrawal = false),
                Transaction(amount = 30000, group = "Ajo", date = "12/12/2017", deposit = false, withdrawal = true)
        )
        transactionsView?.hideProgress()
        transactionsView?.showTransactions(transactions)
    }

    override fun detachView() {
        this.transactionsView = null
    }
}