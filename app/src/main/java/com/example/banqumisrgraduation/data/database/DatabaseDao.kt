package com.example.banquemisr.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DatabaseDao {

    @Query("SELECT * FROM orders where userid= :id")
    fun getAll(id:Int): List<DatabaseTable>

    @Insert
    fun insertAll(orders: List<DatabaseTable>)

    @Query("Delete from orders where userid= :id")
    fun delete(id:Int)

}