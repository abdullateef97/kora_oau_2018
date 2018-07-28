package com.thanos.kontribute.ui.group_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class GroupDetailTabAdapter(fm: FragmentManager, private val fragments: ArrayList<Fragment>,
                            private val titles: ArrayList<String>): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}
