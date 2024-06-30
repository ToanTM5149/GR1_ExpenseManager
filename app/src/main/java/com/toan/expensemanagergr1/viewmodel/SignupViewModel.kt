package com.toan.expensemanagergr1.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toan.expensemanagergr1.data.AppDatabase
import com.toan.expensemanagergr1.data.dao.UserDao
import com.toan.expensemanagergr1.data.model.UserEntity

class SignupViewModel(private val dao: UserDao) : ViewModel() {

    suspend fun signupUser(username: String, password: String, role: String, phone: String, email: String): Boolean {
        return try {
            val existingUser = dao.getUserByUsernameAndPassword(username, password)
            if (existingUser == null) {
                val newUser = UserEntity(
                    username = username,
                    password = password,
                    role = role,
                    created_at = System.currentTimeMillis(),
                    phone = phone,
                    email = email
                )
                dao.insertUser(newUser)
                true
            } else {
                false
            }
        } catch (ex: Throwable) {
            false
        }
    }
}

class SignupViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            val dao = AppDatabase.getDatabase(context).userDao()
            @Suppress("UNCHECKED_CAST")
            return SignupViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}