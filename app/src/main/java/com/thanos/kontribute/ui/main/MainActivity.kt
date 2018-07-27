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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        HomeFragment.OnHomeFragmentInteractionListener,
        ProfileFragment.OnProfileFragmentInteractionListener{


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val selectedFragment: Fragment?
        when (item.itemId) {
            R.id.navigation_home -> {
                selectedFragment = supportFragmentManager.findFragmentByTag(TAG_HOME) ?: HomeFragment()
                loadFragment(selectedFragment, TAG_HOME)
                return@OnNavigationItemSelectedListener true
            }
//            R.id.navigation_sermons -> {
//                selectedFragment = supportFragmentManager.findFragmentByTag(TAG_SERMONS) ?: SermonFragment()
//                loadFragment(selectedFragment, TAG_SERMONS)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_blog -> {
//                selectedFragment = supportFragmentManager.findFragmentByTag(TAG_BLOG) ?: BlogListFragment()
//                loadFragment(selectedFragment, TAG_BLOG)
//                return@OnNavigationItemSelectedListener true
//            }
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
        registerJob()
    }

    private fun registerJob() {

//        val myJob = jobDispatcher.newJobBuilder()
//                .setService(DailyService::class.java)
//                .setRecurring(false)
//                .setTag(TAG_DAILY_SERVICE)
//                .setLifetime(Lifetime.FOREVER)
//                .setTrigger(Trigger.NOW)
//                .setReplaceCurrent(false)
//                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
//                .build()
//
//        jobDispatcher.mustSchedule(myJob)
    }

    private fun loadFragment(fragment: Fragment?, tag: String) {
        fragment?.let {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content_container, fragment, tag)
                    .commit()
        }
    }



}
