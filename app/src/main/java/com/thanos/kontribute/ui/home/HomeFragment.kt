package com.thanos.kontribute.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Group
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : androidx.fragment.app.Fragment(), HomeContract.HomeView, PrayerPlanListAdapter.PrayerPlanListListener {

    private var listener: OnHomeFragmentInteractionListener? = null
    private lateinit var prayerPlanListAdapter: PrayerPlanListAdapter
    private var prayerPlans: ArrayList<Group> = ArrayList()

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

        //Category buttons onClickListener
    }

    override fun onResume() {
        super.onResume()
    }

    private fun onCategoryButtonClicked(view: View) {
    }

    private fun setUpAdapter(addPadding: Boolean) {
        prayerPlanListAdapter = PrayerPlanListAdapter(prayerPlans, this)
        rvPrayerPlan.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvPrayerPlan.adapter = prayerPlanListAdapter
        rvPrayerPlan.onFlingListener = null //Solve { An instance of OnFlingListener already set } error
        LinearSnapHelper().attachToRecyclerView(rvPrayerPlan)
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

    interface OnHomeFragmentInteractionListener {
    }


}
