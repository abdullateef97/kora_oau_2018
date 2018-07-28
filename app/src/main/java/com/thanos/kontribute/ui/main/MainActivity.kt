package com.thanos.kontribute.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.thanos.kontribute.helper.TAG_HOME
import com.thanos.kontribute.helper.TAG_PROFILE
import com.thanos.kontribute.ui.home.HomeFragment
import com.thanos.kontribute.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.helper.TAG_TRANSACTIONS
import com.thanos.kontribute.ui.transactions.TransactionsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        HomeFragment.OnHomeFragmentInteractionListener,
        TransactionsFragment.OnFragmentInteractionListener,
        ProfileFragment.OnProfileFragmentInteractionListener{


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val selectedFragment: Fragment?
        when (item.itemId) {
            R.id.navigation_home -> {
                selectedFragment = supportFragmentManager.findFragmentByTag(TAG_HOME) ?: HomeFragment()
                loadFragment(selectedFragment, TAG_HOME)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_transactions -> {
                selectedFragment = supportFragmentManager.findFragmentByTag(TAG_TRANSACTIONS) ?: TransactionsFragment()
                loadFragment(selectedFragment, TAG_TRANSACTIONS)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                selectedFragment = supportFragmentManager.findFragmentByTag(TAG_PROFILE) ?: ProfileFragment()
                loadFragment(selectedFragment, TAG_PROFILE)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.getInstance().getAppComponent().inject(this)

        //load default fragment
        loadFragment(HomeFragment(), TAG_HOME)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun loadFragment(fragment: Fragment?, tag: String) {
        fragment?.let {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content_container, fragment, tag)
                    .commit()
        }
    }



}
