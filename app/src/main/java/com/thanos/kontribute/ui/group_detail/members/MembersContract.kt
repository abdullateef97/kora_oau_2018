package com.thanos.kontribute.ui.group_detail.members

import com.thanos.kontribute.data.model.Member
import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface MembersContract {

    interface MembersPresenter: BasePresenter<MembersView> {
        fun fetchGroupMembers()
    }

    interface MembersView: BaseView {
        fun showProgress()
        fun hideProgress()
        fun showGroupMembers(members: ArrayList<Member>)
    }
}