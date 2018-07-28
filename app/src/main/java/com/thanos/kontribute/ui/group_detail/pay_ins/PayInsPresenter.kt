package com.thanos.kontribute.ui.group_detail.pay_ins

import com.thanos.kontribute.data.model.Transaction

class PayInsPresenter : PayInsContract.PayInsPresenter {

    private var payInsView: PayInsContract.PayInsView? = null

    override fun attachView(view: PayInsContract.PayInsView) {
        payInsView = view
    }

    override fun fetchGroupPayIns() {
        payInsView?.showProgress()
        val payIns = arrayListOf(
                Transaction(amount = 90000, group = "Main One", date = "25/04/2018", deposit = true, withdrawal = false),
                Transaction(amount = 90000, group = "Germany Straight", date = "22/03/2018", deposit = true, withdrawal = false),
                Transaction(amount = 4500, group = "Main One", date = "28/01/2018", deposit = true, withdrawal = false),
                Transaction(amount = 30000, group = "Ajo", date = "12/12/2017", deposit = false, withdrawal = true),
                Transaction(amount = 90000, group = "Main One", date = "25/04/2018", deposit = true, withdrawal = false),
                Transaction(amount = 90000, group = "Germany Straight", date = "22/03/2018", deposit = true, withdrawal = false),
                Transaction(amount = 4500, group = "Main One", date = "28/01/2018", deposit = true, withdrawal = false),
                Transaction(amount = 30000, group = "Ajo", date = "12/12/2017", deposit = false, withdrawal = true)
        )
        payInsView?.hideProgress()
        payInsView?.showGroupPayIns(payIns)
    }

    override fun detachView() {
        payInsView = null
    }
}