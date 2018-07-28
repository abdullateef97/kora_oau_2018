package com.thanos.kontribute.ui.profile

import android.os.Handler
import com.atlascc.kontribute.data.remote.ApiService
import com.thanos.kontribute.ui.profile.ProfileContract

class ProfilePresenter(apiService: ApiService) : ProfileContract.ProfilePresenter {

    private var profileView: ProfileContract.ProfileView? = null

    override fun updateProfile() {
        profileView?.showProgress()
        Handler().postDelayed({
            profileView?.hideProgress()
            profileView?.onProfileUpdateSuccessful()
        }, 3000)
    }

    override fun attachView(view: ProfileContract.ProfileView) {
        this.profileView = view
    }

    override fun detachView() {
        this.profileView = null
    }
}