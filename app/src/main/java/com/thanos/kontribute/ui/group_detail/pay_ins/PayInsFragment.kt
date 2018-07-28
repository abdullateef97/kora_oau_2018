package com.thanos.kontribute.ui.group_detail.pay_ins

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
import com.thanos.kontribute.ui.transactions.TransactionListAdapter
import kotlinx.android.synthetic.main.fragment_transactions.*
import javax.inject.Inject

class PayInsFragment : Fragment(), PayInsContract.PayInsView {

    private lateinit var payInsListAdapter: PayInsListAdapter
    private var payIns: ArrayList<Transaction> = ArrayList()

    @Inject
    lateinit var payInsPresenter: PayInsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getInstance().getAppComponent().inject(this)
        payInsPresenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        payInsPresenter.fetchGroupPayIns()
    }

    private fun setUpAdapter() {
        payInsListAdapter = PayInsListAdapter(payIns)
        rvTransactions.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.VERTICAL, false)
        rvTransactions.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rvTransactions.adapter = payInsListAdapter
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showGroupPayIns(transactions: ArrayList<Transaction>) {
        payInsListAdapter.updateTransactions(transactions)
    }
}
