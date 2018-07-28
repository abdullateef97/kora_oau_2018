package com.thanos.kontribute.ui.transactions

import android.content.Context
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

class TransactionsFragment : Fragment(),
        TransactionsContract.TransactionsView,
        TransactionListAdapter.TransactionListListener {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var transactionListAdapter: TransactionListAdapter
    private var transactions: ArrayList<Transaction> = ArrayList()

    @Inject
    lateinit var transactionsPresenter: TransactionsPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.getInstance().getAppComponent().inject(this)
        transactionsPresenter.attachView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        transactionsPresenter.fetchTransactions()
    }

    private fun setUpAdapter() {
        transactionListAdapter = TransactionListAdapter(transactions, this)
        rvTransactions.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.VERTICAL, false)
        rvTransactions.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        rvTransactions.adapter = transactionListAdapter
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showTransactions(transactions: ArrayList<Transaction>) {
        transactionListAdapter.updateTransactions(transactions)
    }

    override fun onTransactionSelected(transaction: Transaction) {

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {

    }

}
