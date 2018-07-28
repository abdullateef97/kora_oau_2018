package com.thanos.kontribute.ui.group_detail.pay_outs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thanos.kontribute.App
import com.thanos.kontribute.R
import com.thanos.kontribute.data.model.Transaction
import kotlinx.android.synthetic.main.fragment_transactions.*
import javax.inject.Inject

class PayOutsFragment : Fragment(), PayOutsContract.PayOutsView {

    private lateinit var payOutsListAdapter: PayOutsListAdapter
    private var payOuts: ArrayList<Transaction> = ArrayList()

    @Inject
    lateinit var payOutsPresenter: PayOutsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getInstance().getAppComponent().inject(this)
        payOutsPresenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        payOutsPresenter.fetchGroupPayOuts()
    }

    private fun setUpAdapter() {
        payOutsListAdapter = PayOutsListAdapter(payOuts)
        rvTransactions.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.VERTICAL, false)
        rvTransactions.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rvTransactions.adapter = payOutsListAdapter
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showGroupPayouts(transactions: ArrayList<Transaction>) {
        payOutsListAdapter.updateTransactions(transactions)
    }
}
