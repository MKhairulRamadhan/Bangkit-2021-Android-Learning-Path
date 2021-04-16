package com.mkhairulramadhan.githubuser.db.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkhairulramadhan.githubuser.db.network.model.Items

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user_fav: Items?): Long

    @Query("SELECT * FROM user_fav WHERE id= :id")
    fun getUserById(id: Int?): Cursor

    @Query("SELECT * FROM user_fav ORDER BY login ASC")
    fun getUsers(): Cursor

    @Query("DELETE FROM user_fav WHERE id = :id")
    fun deleteUser(id: Int?): Int

}