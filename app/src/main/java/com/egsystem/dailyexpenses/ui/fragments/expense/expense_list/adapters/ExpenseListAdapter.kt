package com.egsystem.dailyexpenses.ui.fragments.expense.expense_list.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.egsystem.dailyexpenses.R
import com.egsystem.dailyexpenses.data.models.Expense
import com.egsystem.dailyexpenses.ui.fragments.expense.expense_list.ExpenseListFragmentDirections
import kotlinx.android.synthetic.main.recycler_expense_item.view.*

class ExpenseListAdapter : RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder>() {

    var ExpensesList = emptyList<Expense>()

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.cv_cardView.setOnClickListener {
                val position = adapterPosition
                Log.d("ExpensesListAdapter", "Item clicked at: $position")

                val action: NavDirections =
                ExpenseListFragmentDirections.actionExpenseListFragmentToUpdateExpenseFragment(ExpensesList[position])
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_expense_item, parent,false))
    }

    //todo: initialise the recycler view and set it up to show data (part2)
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val currentExpense = ExpensesList[position]
        holder.itemView.iv_Expense_icon.setImageResource(currentExpense.imageId)
        holder.itemView.tv_item_description.text = currentExpense.expense_description
//        holder.itemView.tv_timeElapsed.text =
//            Calculations.calculateTimeBetweenDates(currentExpense.expense_startTime)
        holder.itemView.tv_item_createdTimeStamp.text = "Since: ${currentExpense.expense_startTime}"
        holder.itemView.tv_item_title.text = "${currentExpense.expense_amount}"
    }

    override fun getItemCount(): Int {
        return ExpensesList.size
    }

    fun setData(Expense: List<Expense>) {
        this.ExpensesList = Expense
        notifyDataSetChanged()
    }


}