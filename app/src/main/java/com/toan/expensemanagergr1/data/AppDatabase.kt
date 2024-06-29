package com.toan.expensemanagergr1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.toan.expensemanagergr1.data.dao.ExpenseDao
import com.toan.expensemanagergr1.data.dao.UserDao
import com.toan.expensemanagergr1.data.model.ExpenseEntity
import com.toan.expensemanagergr1.data.model.UserEntity


@Database(entities = [ExpenseEntity::class, UserEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "expense_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .addMigrations(MIGRATION_2_3)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_table ADD COLUMN phone TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE user_table ADD COLUMN email TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}