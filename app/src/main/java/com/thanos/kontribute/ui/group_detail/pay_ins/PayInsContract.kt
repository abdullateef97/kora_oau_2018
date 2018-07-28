package com.thanos.kontribute.ui.group_detail.pay_ins

import com.thanos.kontribute.data.model.Transaction
import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface PayInsContract {

    interface PayInsPresenter: BasePresenter<PayInsView> {
        fun fetchGroupPayIns()
    }

    interface PayInsView: BaseView {
        fun showProgress()
        fun hideProgress()
        fun showGroupPayIns(transactions: ArrayList<Transaction>)
    }
}