package com.toan.expensemanagergr1.viewmodel.loginandsignup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toan.expensemanagergr1.data.db.AppDatabase
import com.toan.expensemanagergr1.data.dao.UserDao
import com.toan.expensemanagergr1.data.model.UserEntity

class SignupViewModel(private val dao: UserDao) : ViewModel() {

    suspend fun signupUser(username: String, password: String, role: String, phone: String, email: String): Result<Int> {
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
                val userId = dao.getUserByUsernameAndPassword(username, password)?.id ?: throw Exception("Lỗi khi tạo người dùng")
                Result.success(userId)
            } else {
                Result.failure(Exception("Người dùng đã tồn tại"))
            }
        } catch (ex: Throwable) {
            Result.failure(ex)
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