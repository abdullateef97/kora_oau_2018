package com.thanos.kontribute.ui.group_detail.pay_outs

import com.thanos.kontribute.data.model.Transaction

class PayOutsPresenter : PayOutsContract.PayOutsPresenter {

    private var payOutsView: PayOutsContract.PayOutsView? = null

    override fun attachView(view: PayOutsContract.PayOutsView) {
        payOutsView = view
    }

    override fun fetchGroupPayOuts() {
        payOutsView?.showProgress()
        val payOuts = arrayListOf(
                Transaction(amount = 90000, group = "Main One", date = "25/04/2018", deposit = true, withdrawal = false),
                Transaction(amount = 90000, group = "Germany Straight", date = "22/03/2018", deposit = true, withdrawal = false),
                Transaction(amount = 4500, group = "Main One", date = "28/01/2018", deposit = true, withdrawal = false),
                Transaction(amount = 30000, group = "Ajo", date = "12/12/2017", deposit = false, withdrawal = true),
                Transaction(amount = 90000, group = "Main One", date = "25/04/2018", deposit = true, withdrawal = false),
                Transaction(amount = 90000, group = "Germany Straight", date = "22/03/2018", deposit = true, withdrawal = false),
                Transaction(amount = 4500, group = "Main One", date = "28/01/2018", deposit = true, withdrawal = false),
                Transaction(amount = 30000, group = "Ajo", date = "12/12/2017", deposit = false, withdrawal = true)
        )
        payOutsView?.hideProgress()
        payOutsView?.showGroupPayouts(payOuts)
    }

    override fun detachView() {
        payOutsView = null
    }
}