package com.thanos.kontribute.ui.profile

import com.thanos.kontribute.ui.base.BasePresenter
import com.thanos.kontribute.ui.base.BaseView

interface ProfileContract {

    interface ProfilePresenter: BasePresenter<ProfileView> {
        fun updateProfile()
    }

    interface ProfileView: BaseView {
        fun onProfileUpdateSuccessful()
        fun showProgress()
        fun hideProgress()

    }
}