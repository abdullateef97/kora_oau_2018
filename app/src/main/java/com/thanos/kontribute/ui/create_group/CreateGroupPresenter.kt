package com.thanos.kontribute.ui.create_group

import com.thanos.kontribute.data.model.Transaction
import com.thanos.kontribute.ui.create_group.CreateGroupContract

class CreateGroupPresenter : CreateGroupContract.CreateGroupPresenter {

    private var createGroupView: CreateGroupContract.CreateGroupView? = null

    override fun attachView(view: CreateGroupContract.CreateGroupView) {
        this.createGroupView = view
    }


    override fun detachView() {
        this.createGroupView = null
    }
}