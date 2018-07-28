package com.thanos.kontribute.ui.group_detail.members

import com.thanos.kontribute.data.model.Member

class MembersPresenter : MembersContract.MembersPresenter {

    private var membersView: MembersContract.MembersView? = null

    override fun attachView(view: MembersContract.MembersView) {
        membersView = view
    }

    override fun fetchGroupMembers() {
        membersView?.showProgress()
        val members = arrayListOf(
                Member("", "Quadri Anifowose", "", false),
                Member("", "Lateef Adeniran", "", false),
                Member("", "Lanre Teriba", "", false),
                Member("", "Mayomi Ayandiran", "", true),
                Member("", "Rasheed Sulayman", "", false),
                Member("", "Emmanuel Kehinde", "", true),
                Member("", "Isreal Arunah", "", false)
        )
        membersView?.hideProgress()
        membersView?.showGroupMembers(members)
    }

    override fun detachView() {
        membersView = null
    }
}