package com.egsystem.dailyincomes.ui.fragments.income.income_list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.egsystem.dailyexpenses.R
import com.egsystem.dailyexpenses.data.models.Income
import com.egsystem.dailyexpenses.ui.fragments.expense.expense_list.adapters.IncomeListAdapter
import com.egsystem.dailyexpenses.ui.viewmodels.IncomeViewModel

import kotlinx.android.synthetic.main.fragment_income_list.*


class IncomeListFragment : Fragment(R.layout.fragment_income_list) {

    private lateinit var incomeList: List<Income>
    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var adapter: IncomeListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = IncomeListAdapter()
        rv_incomes.adapter = adapter
        rv_incomes.layoutManager = LinearLayoutManager(context)

        //Instantiate and create viewmodel observers
        viewModels()

        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_incomeListFragment_to_addIncomeFragment)
        }

        //Show the options menu in this fragment
        setHasOptionsMenu(true)

        swipeToRefresh.setOnRefreshListener {
            adapter.setData(incomeList)
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun viewModels() {
        incomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)

        incomeViewModel.getAllIncomes.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            incomeList = it

            if (it.isEmpty()) {
                rv_incomes.visibility = View.GONE
                tv_emptyView.visibility = View.VISIBLE
            } else {
                rv_incomes.visibility = View.VISIBLE
                tv_emptyView.visibility = View.GONE
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> incomeViewModel.deleteAllIncomes()
        }
        return super.onOptionsItemSelected(item)
    }

}