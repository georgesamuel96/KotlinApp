package com.example.georgesamuel.kotlinapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.georgesamuel.kotlinapp.data.db.entities.CURRENT_USER_ID
import com.example.georgesamuel.kotlinapp.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(user: User) : Long

    @Query("SELECT * FROM user where uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}