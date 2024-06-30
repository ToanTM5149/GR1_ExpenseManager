package com.toan.expensemanagergr1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toan.expensemanagergr1.data.AppDatabase
import com.toan.expensemanagergr1.data.dao.UserDao
import com.toan.expensemanagergr1.data.model.UserEntity

class LoginViewModel(val dao : UserDao) : ViewModel() {

    suspend fun loginUser(username: String, password: String): Result<Int> {
        return try {
            val user = dao.getUserByUsernameAndPassword(username, password)
            if (user != null) {
                Result.success(user.id)
            } else {
                Result.failure(Exception("Người dùng không tồn tại hoặc sai thông tin đăng nhập"))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
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