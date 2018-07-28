package com.thanos.kontribute.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thanos.kontribute.helper.hideProgressDialog
import com.thanos.kontribute.helper.showProgressDialog
import com.thanos.kontribute.helper.showToast
import com.atlascc.kontribute.util.ImageUtil
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : androidx.fragment.app.Fragment(), ProfileContract.ProfileView {

    @Inject
    lateinit var profilePresenter: ProfilePresenter

    @Inject
    lateinit var imageUtil: ImageUtil

    private var listener: OnProfileFragmentInteractionListener? = null
    private var editing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getInstance().getAppComponent().inject(this)
        profilePresenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageUtil.loadImageFromDrawable(R.drawable.avatar,imgProfileImage)
        btnEdit.setOnClickListener { enableEditing() }
        btnSave.setOnClickListener { profilePresenter.updateProfile() }
    }

    private fun enableEditing() {
        edtEmail.isEnabled = true
        edtFullName.isEnabled = true
        edtBank.isEnabled = true
        btnSave.visibility = View.VISIBLE
        btnEdit.visibility = View.INVISIBLE
        editing = true
    }

    private fun disableEditing() {
        edtEmail.isEnabled = false
        edtFullName.isEnabled = false
        edtBank.isEnabled = false
        btnSave.visibility = View.INVISIBLE
        btnEdit.visibility = View.VISIBLE
        editing = false
    }

    override fun onProfileUpdateSuccessful() {
        context?.showToast("Profile updated successfully")
        disableEditing()
    }

    override fun showProgress() {
        context?.showProgressDialog()
    }

    override fun hideProgress() {
        context?.hideProgressDialog()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnProfileFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnProfileFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnProfileFragmentInteractionListener {
    }

}
