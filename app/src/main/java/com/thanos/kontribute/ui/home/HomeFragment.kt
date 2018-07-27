package com.thanos.kontribute.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Group
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : androidx.fragment.app.Fragment(),
        HomeContract.HomeView,
        GroupListAdapter.GroupListListener {

    private var listener: OnHomeFragmentInteractionListener? = null
    private lateinit var groupListAdapter: GroupListAdapter
    private var groups: ArrayList<Group> = ArrayList()

    @Inject
    lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.getInstance().getAppComponent().inject(this)
        homePresenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        homePresenter.fetchGroups()
    }

    private fun setUpAdapter() {
        groupListAdapter = GroupListAdapter(groups, this)
        rvGroups.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rvGroups.adapter = groupListAdapter
    }

    override fun showGroups(groups: ArrayList<Group>) {
        groupListAdapter.updateGroups(groups)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHomeFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnHomeFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        homePresenter.detachView()
    }

    override fun onGroupSelected(group: Group) {

    }

    interface OnHomeFragmentInteractionListener {
    }


}
