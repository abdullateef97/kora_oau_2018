package com.thanos.kontribute.ui.group_detail.pay_outs

import com.thanos.kontribute.data.model.Transaction
import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface PayOutsContract {

    interface PayOutsPresenter: BasePresenter<PayOutsView> {
        fun fetchGroupPayOuts()
    }

    interface PayOutsView: BaseView {
        fun showProgress()
        fun hideProgress()
        fun showGroupPayouts(transactions: ArrayList<Transaction>)
    }
}