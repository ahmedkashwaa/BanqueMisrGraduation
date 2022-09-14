package com.example.banqumisrgraduation.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.banquemisr.database.DatabaseDao
import com.example.banquemisr.database.DatabaseTable


@Database(version = 1,entities = [DatabaseTable::class])
abstract class OrdersDatabase : RoomDatabase() {
    abstract val dao: DatabaseDao

    companion object {
        @Volatile
        private lateinit var instance: OrdersDatabase

        fun getInstance(context: Context): OrdersDatabase {
            synchronized(OrdersDatabase::class.java) {
                if(!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        OrdersDatabase::class.java,
                        "orders.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }


}