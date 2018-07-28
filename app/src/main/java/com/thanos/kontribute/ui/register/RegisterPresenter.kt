package com.thanos.kontribute.ui.register

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.thanos.kontribute.data.model.User
import com.thanos.kontribute.helper.isValidEmail
import com.thanos.kontribute.helper.isValidPassword

class RegisterPresenter(private var firebaseAuth: FirebaseAuth,
                        private var firestore: FirebaseFirestore):
        RegisterContract.RegisterPresenter {

    private var registerView: RegisterContract.RegisterView? = null

    override fun register(user: User, password: String) {
        if (!user.email.isValidEmail()) {
            registerView?.showMessage("Enter a valid email.")
            return
        }

        if (!password.isValidPassword()) {
            registerView?.showMessage("Enter a valid password.")
            return
        }
        registerView?.showProgress()

        firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                .addOnCompleteListener { result ->
                    registerView?.hideProgress()
                    if (result.isSuccessful) {
                        user.id = firebaseAuth.currentUser?.uid!!
                        createUserInDb(user)
                    } else {
                        registerView?.showMessage("Error trying to register your account, try again.")
                    }
                }
    }

    private fun createUserInDb(user: User) {
        firestore.collection("users")
                .document(firebaseAuth.currentUser?.uid!!)
                .set(user)
                .addOnSuccessListener {
                    registerView?.goToMainActivity()
                }
                .addOnFailureListener {
                    registerView?.showMessage("Error trying to register your account, try again.")
                }
    }

    override fun goToLoginActivity() {
        registerView?.goToLoginActivity()
    }

    override fun attachView(view: RegisterContract.RegisterView) {
        this.registerView = view
    }

    override fun detachView() {
        this.registerView = null
    }
}