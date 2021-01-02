package com.egsystem.dailyexpenses.ui.fragments.expense.expense_list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.egsystem.dailyexpenses.R
import com.egsystem.dailyexpenses.data.models.Expense
import com.egsystem.dailyexpenses.ui.fragments.expense.expense_list.adapters.ExpenseListAdapter
import com.egsystem.dailyexpenses.ui.viewmodels.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_expense_list.*


class ExpenseListFragment : Fragment(R.layout.fragment_expense_list) {

    private lateinit var expenseList: List<Expense>
    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var adapter: ExpenseListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ExpenseListAdapter()
        rv_expenses.adapter = adapter
        rv_expenses.layoutManager = LinearLayoutManager(context)

        //Instantiate and create viewmodel observers
        viewModels()

        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_expenseListFragment_to_addExpenseFragment)
        }

        //Show the options menu in this fragment
        setHasOptionsMenu(true)

        swipeToRefresh.setOnRefreshListener {
            adapter.setData(expenseList)
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun viewModels() {
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        expenseViewModel.getAllExpenses.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            expenseList = it

            if (it.isEmpty()) {
                rv_expenses.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_expenses.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> expenseViewModel.deleteAllExpenses()
        }
        return super.onOptionsItemSelected(item)
    }

}