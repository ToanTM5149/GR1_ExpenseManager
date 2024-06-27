package com.toan.expensemanagergr1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toan.expensemanagergr1.R
import com.toan.expensemanagergr1.widget.Ultis
import com.toan.expensemanagergr1.data.AppDatabase
import com.toan.expensemanagergr1.data.dao.ExpenseDao
import com.toan.expensemanagergr1.data.model.ExpenseEntity

class TotalInfoViewModel(dao : ExpenseDao) : ViewModel() {
    val expenses = dao.getAllExpense()

    fun getBalance(list: List<ExpenseEntity>) : String{
        var total = 0.0
        list.forEach {
            if (it.type == "Thu nhập") {
                total += it.amount
            } else {
                total -= it.amount
            }
        }
        return "$ ${Ultis.formatToDecimalValue(total)}"
    }

    fun getTotalExpense(list: List<ExpenseEntity>) : String {
        var total = 0.0
        list.forEach {
            if (it.type == "Chi phí") {
                total += it.amount
            }
        }
        return "$ ${Ultis.formatToDecimalValue(total)}"
    }

    fun getTotalIncome(list: List<ExpenseEntity>) : String {
        var total = 0.0
        list.forEach {
            if (it.type == "Thu nhập") {
                total += it.amount
            }
        }
        return "$ ${Ultis.formatToDecimalValue(total)}"
    }

    fun getItemIcon(item : ExpenseEntity) : Int {
        if (item.category == "Salary") {
            return R.drawable.ic_paypal
        } else if (item.category == "Netflix") {
            return R.drawable.ic_netflix
        } else if (item.category == "Starbucks") {
            return R.drawable.ic_starbuck
        }
        return R.drawable.ic_upwork
    }
}

class TotalInfoViewModelFactory(private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TotalInfoViewModel::class.java)) {
            val dao = AppDatabase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return TotalInfoViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}