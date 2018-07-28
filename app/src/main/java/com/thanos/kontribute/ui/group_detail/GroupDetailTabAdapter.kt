package com.thanos.kontribute.ui.group_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class GroupDetailTabAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    var fragments = arrayListOf<Fragment>()
    var titles = arrayListOf<String>()

    fun addFragment (fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

}
