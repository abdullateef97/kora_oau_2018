package com.thanos.kontribute.ui.group_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.ui.group_detail.members.MembersFragment
import com.thanos.kontribute.ui.group_detail.pay_ins.PayInsFragment
import com.thanos.kontribute.ui.group_detail.pay_outs.PayOutsFragment
import kotlinx.android.synthetic.main.activity_group_detail.*

class GroupDetailActivity : AppCompatActivity(),
        MembersFragment.OnListFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_detail)

        App.getInstance().getAppComponent().inject(this)

        val fragments = arrayListOf<Fragment>(MembersFragment(), PayInsFragment(), PayOutsFragment())
        val titles = arrayListOf("Members", "PayIns", "PayOuts")

        viewPager.adapter = GroupDetailTabAdapter(supportFragmentManager, fragments, titles)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onListFragmentInteraction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
