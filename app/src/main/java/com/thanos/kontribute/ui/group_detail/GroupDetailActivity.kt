package com.thanos.kontribute.ui.group_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Group
import com.thanos.kontribute.helper.toNaira
import com.thanos.kontribute.ui.deposit.DepositActivity
import com.thanos.kontribute.ui.group_detail.members.MembersFragment
import com.thanos.kontribute.ui.group_detail.pay_ins.PayInsFragment
import com.thanos.kontribute.ui.group_detail.pay_outs.PayOutsFragment
import com.thanos.kontribute.ui.withdrawal.WithdrawalActivity
import io.github.kobakei.materialfabspeeddial.FabSpeedDialMenu
import kotlinx.android.synthetic.main.activity_group_detail.*

class GroupDetailActivity : AppCompatActivity(),
        MembersFragment.OnListFragmentInteractionListener {

    private val isAdmin: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        App.getInstance().getAppComponent().inject(this)
        setUpFab()

        txt_view_amount.text = 50000.toNaira()
        val group = intent.getParcelableExtra<Group>("group")
        txt_view_group_name.text = group.title
        txt_view_group_desc.text = group.description

        val fragments = arrayListOf<Fragment>(MembersFragment(), PayInsFragment(), PayOutsFragment())
        val titles = arrayListOf("Members", "PayIns", "PayOuts")

        viewPager.adapter = GroupDetailTabAdapter(supportFragmentManager, fragments, titles)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setUpFab() {
        val DEPOSIT = "Deposit"
        val WITHDRAW = "Withdraw"
        val ADD_MEMBER = "Add new member"

        val menu = FabSpeedDialMenu(this)
        menu.add(DEPOSIT).setIcon(R.drawable.ic_toc_black_24dp)
        if (isAdmin) {
            menu.add(WITHDRAW).setIcon(R.drawable.ic_toc_black_24dp)
            menu.add(ADD_MEMBER).setIcon(R.drawable.ic_toc_black_24dp)
        }

        fab.setMenu(menu)

        fab.addOnStateChangeListener {
            // do something
        }

        fab.addOnMenuItemClickListener { fab, textView, itemId ->
            // do something
            when(textView?.text) {
                DEPOSIT -> {
                    startActivity(Intent(this@GroupDetailActivity, DepositActivity::class.java))
                }
                WITHDRAW -> {
                    startActivity(Intent(this@GroupDetailActivity, WithdrawalActivity::class.java))

                }
                ADD_MEMBER -> {
                    startActivity(Intent(this@GroupDetailActivity, AddMemberActivity::class.java))
                }
            }
        }
    }


    override fun onListFragmentInteraction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
