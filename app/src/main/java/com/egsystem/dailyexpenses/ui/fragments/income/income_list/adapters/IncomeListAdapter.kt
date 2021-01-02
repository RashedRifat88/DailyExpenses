package com.egsystem.dailyexpenses.ui.fragments.expense.expense_list.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.egsystem.dailyexpenses.R
import com.egsystem.dailyexpenses.data.models.Income
import com.egsystem.dailyincomes.ui.fragments.income.income_list.IncomeListFragmentDirections
import kotlinx.android.synthetic.main.recycler_expense_item.view.cv_cardView
import kotlinx.android.synthetic.main.recycler_expense_item.view.tv_item_createdTimeStamp
import kotlinx.android.synthetic.main.recycler_expense_item.view.tv_item_description
import kotlinx.android.synthetic.main.recycler_expense_item.view.tv_item_title
import kotlinx.android.synthetic.main.recycler_income_item.view.*

class IncomeListAdapter : RecyclerView.Adapter<IncomeListAdapter.IncomeViewHolder>() {

    var IncomesList = emptyList<Income>()

    inner class IncomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.cv_cardView.setOnClickListener {
                val position = adapterPosition
                Log.d("IncomesListAdapter", "Item clicked at: $position")

                val action: NavDirections =
                IncomeListFragmentDirections.actionIncomeListFragmentToUpdateIncomeFragment(IncomesList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        return IncomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_income_item, parent,false))
    }

    //todo: initialise the recycler view and set it up to show data (part2)
    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        val currentIncome = IncomesList[position]
        holder.itemView.iv_Income_icon.setImageResource(currentIncome.imageId)
        holder.itemView.tv_item_description.text = currentIncome.income_description
//        holder.itemView.tv_timeElapsed.text =
//            Calculations.calculateTimeBetweenDates(currentIncome.income_startTime)
        holder.itemView.tv_item_createdTimeStamp.text = "Since: ${currentIncome.income_startTime}"
        holder.itemView.tv_item_title.text = "${currentIncome.income_amount}"
    }

    override fun getItemCount(): Int {
        return IncomesList.size
    }

    fun setData(Income: List<Income>) {
        this.IncomesList = Income
        notifyDataSetChanged()
    }


}