package com.toan.expensemanagergr1.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.toan.expensemanagergr1.R
import com.toan.expensemanagergr1.widget.Ultis
import com.toan.expensemanagergr1.data.AppDatabase
import com.toan.expensemanagergr1.data.dao.ExpenseDao
import com.toan.expensemanagergr1.data.model.ExpenseEntity
import com.toan.expensemanagergr1.data.model.UserWithExpenses
import kotlinx.coroutines.launch

class TotalInfoViewModel(private val dao : ExpenseDao) : ViewModel() {

    private val _userWithExpenses = MutableLiveData<UserWithExpenses>()
    val userWithExpenses: LiveData<UserWithExpenses> get() = _userWithExpenses

    private val _balance = MutableLiveData<String>()
    val balance: LiveData<String> get() = _balance

    private val _totalExpense = MutableLiveData<String>()
    val totalExpense: LiveData<String> get() = _totalExpense

    private val _totalIncome = MutableLiveData<String>()
    val totalIncome: LiveData<String> get() = _totalIncome

    fun loadExpenses(userId: Int) {
        viewModelScope.launch {
            val data = dao.getUserWithExpenses(userId)
            _userWithExpenses.postValue(data)
            getBalance(userId)
            getTotalExpense(userId)
            getTotalIncome(userId)
        }
    }

    suspend fun getBalance(userId: Int) {
        val expenses = dao.getUserWithExpenses(userId)

        var total = 0.0
        expenses.expenses.forEach {
            if (it.type == "Thu nhập") {
                total += it.amount
            } else {
                total -= it.amount
            }
        }
        _balance.postValue("$ ${Ultis.formatToDecimalValue(total)}")
    }

    suspend fun getTotalExpense(userId: Int) {
        val expenses = dao.getUserWithExpenses(userId)
        var total = 0.0
        expenses.expenses.forEach {
            if (it.type == "Chi phí") {
                total += it.amount
            }
        }
        _totalExpense.postValue("$ ${Ultis.formatToDecimalValue(total)}")
    }

    suspend fun getTotalIncome(userId: Int){
        val expenses = dao.getUserWithExpenses(userId)
        var total = 0.0
        expenses.expenses.forEach {
            if (it.type == "Thu nhập") {
                total += it.amount
            }
        }
        _totalIncome.postValue("$ ${Ultis.formatToDecimalValue(total)}").toString()
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

    fun deleteExpense(expense: ExpenseEntity) {
        viewModelScope.launch {
            dao.deleteExpense(expense)
            // Refresh the data
            _userWithExpenses.value?.let { loadExpenses(it.user.id) }
        }
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