package com.egsystem.dailyexpenses.ui.fragments.terms_conditions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.egsystem.dailyexpenses.R
import kotlinx.android.synthetic.main.fragment_home.*


class TermConditionFragment : Fragment(R.layout.fragment_term_condition) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickEvent()
    }

    private fun clickEvent() {
//        tv_expense.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_expenseListFragment)
//        }
//
//        tv_income.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_incomeListFragment)
//        }

    }

}