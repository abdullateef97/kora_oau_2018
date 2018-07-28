package com.thanos.kontribute.ui.home

import com.amulyakhare.textdrawable.util.ColorGenerator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.thanos.kontribute.data.model.Group

class HomePresenter(var firebaseAuth: FirebaseAuth,var firestore: FirebaseFirestore) : HomeContract.HomePresenter {

    private var transactionsView: HomeContract.HomeView? = null

    override fun attachView(view: HomeContract.HomeView) {
        this.transactionsView = view
    }

    override fun fetchGroups() {
//        transactionsView?.showProgress()
//        val groups = ArrayList<Group>()
//        firestore.collection("groups")
//                .get()
//                .addOnSuccessListener {
//                    querySnapshot ->
//                    for (shot in querySnapshot.documents) {
//                        if (shot.getDocumentRefere)
//                    }
//                    transactionsView?.hideProgress()
//                    transactionsView?.showGroups(groups)
//
//
//                }
//                .addOnFailureListener {
//                    transactionsView?.showMessage("Group Fetching failed: " + it.localizedMessage)
//                    transactionsView?.hideProgress()
//                }

        val groups = arrayListOf(
                Group(title = "FYB Trip", description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Trip to Dubai", description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Let\'s go to london", description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "We gather dey", description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Main One", description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor),
                Group(title = "Germany Straight!", description = "dkhckjdkj djhfdkfh dkhfkdhfd dhk", color = ColorGenerator.MATERIAL.randomColor)
        )
        transactionsView?.hideProgress()
        transactionsView?.showGroups(groups)
    }

    override fun detachView() {
        this.transactionsView = null
    }
}