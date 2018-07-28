package com.thanos.kontribute.ui.group_detail.members

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Member
import kotlinx.android.synthetic.main.fragment_members.*
import javax.inject.Inject


class MembersFragment : Fragment(), MembersContract.MembersView,
        MembersListAdapter.MembersListListener {

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var membersListAdapter: MembersListAdapter
    private var members: ArrayList<Member> = ArrayList()

    @Inject
    lateinit var membersPresenter: MembersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getInstance().getAppComponent().inject(this)
        membersPresenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        membersPresenter.fetchGroupMembers()
    }

    private fun setUpAdapter() {
        membersListAdapter = MembersListAdapter(members, this)
        rvMembers.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.VERTICAL, false)
        rvMembers.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rvMembers.adapter = membersListAdapter
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showGroupMembers(members: ArrayList<Member>) {
        membersListAdapter.updateMembers(members)
    }

    override fun onMemberSelected(member: Member) {
        Toast.makeText(context, member.name, Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction()
    }

}
