package com.toan.expensemanagergr1.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithExpenses(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val expenses: List<ExpenseEntity>
)