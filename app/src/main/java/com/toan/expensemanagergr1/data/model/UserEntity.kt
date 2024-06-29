package com.toan.expensemanagergr1.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val role: String,
    val created_at: Long,
    val phone: String,
    val email: String
)