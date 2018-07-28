package com.thanos.kontribute.ui.group_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thanos.kontribute.R
import com.thanos.kontribute.ui.group_detail.dummy.DummyContent
import kotlinx.android.synthetic.main.content_group_detail.*

class GroupDetailActivity : AppCompatActivity(), MembersFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var groupDetailTabAdapter: GroupDetailTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_detail)

        groupDetailTabAdapter = GroupDetailTabAdapter(supportFragmentManager)
        groupDetailTabAdapter.addFragment(MembersFragment(), "Members")
        groupDetailTabAdapter.addFragment(MembersFragment(), "PayIns")
        groupDetailTabAdapter.addFragment(MembersFragment(), "PayOuts")

        viewPager.adapter = groupDetailTabAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
