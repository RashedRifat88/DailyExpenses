package com.egsystem.dailyincomes.ui.fragments.income.create_income

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.egsystem.dailyexpenses.R
import com.egsystem.dailyexpenses.data.models.Income
import com.egsystem.dailyexpenses.logic.utils.Calculations
import com.egsystem.dailyexpenses.ui.viewmodels.IncomeViewModel

import kotlinx.android.synthetic.main.fragment_add_income.*
import java.util.*


class AddIncomeFragment : Fragment(R.layout.fragment_add_income),
    TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var amount = ""
    private var description = ""
    private var incomeCategory = "Not categorised"
    private var drawableSelected = 0
    private var timeStamp = ""

    private lateinit var incomeViewModel: IncomeViewModel

    private var day = 0
    private var month = 0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var cleanDate = ""
    private var cleanTime = ""


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        incomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)

        //Add income to database
        btn_confirm.setOnClickListener {
            addIncomeToDB()
        }
        //Pick a date and time
        pickDateAndTime()

        //Selected and image to put into our list
        drawableSelected()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    //set on click listeners for our data and time pickers
    private fun pickDateAndTime() {
        btn_pickDate.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        btn_pickTime.setOnClickListener {
            getTimeCalendar()
            TimePickerDialog(context, this, hour, minute, true).show()
        }

    }

    private fun addIncomeToDB() {

        //Get text from editTexts
        amount = et_incomeAmount.text.toString()
        description = et_incomeDescription.text.toString()

        //Create a timestamp string for our recyclerview
        timeStamp = "$cleanDate $cleanTime"

        //Check that the form is complete before submitting data to the database
        if (!(amount.isEmpty() || description.isEmpty() || timeStamp.isEmpty() || drawableSelected == 0)) {
            val income = Income(0, amount.toInt(), incomeCategory, description, timeStamp, drawableSelected)

            //add the income if all the fields are filled
            incomeViewModel.addIncome(income)
            Toast.makeText(context, "Income created successfully!", Toast.LENGTH_SHORT).show()

            //navigate back to our home fragment
            findNavController().navigate(R.id.action_addIncomeFragment_to_incomeListFragment)
        } else {
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    // Create a selector for our icons which will appear in the recycler view
    private fun drawableSelected() {
        iv_shopping.setOnClickListener {
            iv_shopping.isSelected = !iv_shopping.isSelected
            drawableSelected = R.drawable.ic_shopping_bag

            incomeCategory = "Shopping"
            tv_selected_cat.text = "Shopping"

            //de-select the other options when we pick an image
            iv_transport.isSelected = false
            iv_bill.isSelected = false

        }

        iv_transport.setOnClickListener {
            iv_transport.isSelected = !iv_transport.isSelected
            drawableSelected = R.drawable.ic_transport

            incomeCategory = "Transport"
            tv_selected_cat.text = "Transport"

            //de-select the other options when we pick an image
            iv_shopping.isSelected = false
            iv_bill.isSelected = false
        }

        iv_bill.setOnClickListener {
            iv_bill.isSelected = !iv_bill.isSelected
            drawableSelected = R.drawable.ic_bill

            incomeCategory = "Bill"
            tv_selected_cat.text = "Bill"

            //de-select the other options when we pick an image
            iv_shopping.isSelected = false
            iv_transport.isSelected = false
        }

    }

    //get the time set
    override fun onTimeSet(TimePicker: TimePicker?, p1: Int, p2: Int) {
        Log.d("Fragment", "Time: $p1:$p2")

        cleanTime = Calculations.cleanTime(p1, p2)
        tv_timeSelected.text = "Time: $cleanTime"
    }

    //get the date set
    override fun onDateSet(p0: DatePicker?, yearX: Int, monthX: Int, dayX: Int) {

        cleanDate = Calculations.cleanDate(dayX, monthX, yearX)
        tv_dateSelected.text = "Date: $cleanDate"
    }

    //get the current time
    private fun getTimeCalendar() {
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
    }

    //get the current date
    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }
}