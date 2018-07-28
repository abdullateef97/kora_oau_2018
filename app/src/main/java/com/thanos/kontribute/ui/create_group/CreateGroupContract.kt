package com.thanos.kontribute.ui.create_group

import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface CreateGroupContract {

    interface CreateGroupPresenter: BasePresenter<CreateGroupView> {
    }

    interface CreateGroupView: BaseView {
        fun showProgress()
        fun hideProgress()
    }
}