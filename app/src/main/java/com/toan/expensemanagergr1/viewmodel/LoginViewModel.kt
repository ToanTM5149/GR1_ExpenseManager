package com.toan.expensemanagergr1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toan.expensemanagergr1.data.AppDatabase
import com.toan.expensemanagergr1.data.dao.UserDao
import com.toan.expensemanagergr1.data.model.UserEntity

class LoginViewModel(val dao : UserDao) : ViewModel() {

    suspend fun addUser(userEntity: UserEntity) : Boolean {
        return try {
            dao.insertUser(userEntity)
            true
        } catch (ex: Throwable) {
            false
        }
    }
}

class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val dao = AppDatabase.getDatabase(context).userDao()
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}