package com.toan.expensemanagergr1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.toan.expensemanagergr1.data.model.ExpenseEntity
import com.toan.expensemanagergr1.data.model.UserWithExpenses
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("select * from expense_table")
    fun getAllExpense(): Flow<List<ExpenseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Update
    suspend fun updateExpense(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)

    @Query("delete from expense_table where userId = :userId")
    suspend fun deleteExpensesByUserId(userId: Int)

    @Transaction
    @Query("select * from user_table where id = :userId")
    suspend fun getUserWithExpenses(userId: Int): UserWithExpenses
}