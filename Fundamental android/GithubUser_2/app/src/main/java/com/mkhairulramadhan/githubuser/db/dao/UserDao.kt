package com.mkhairulramadhan.githubuser.db.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkhairulramadhan.githubuser.db.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserEntity)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUser(): LiveData<List<UserEntity>>

//    @Query("SELECT COUNT() FROM user_table WHERE login= :username")
//    suspend fun getUserByUsername(username: String): MutableLiveData<UserEntity>

    @Query("DELETE FROM user_table WHERE login=:username")
    suspend fun deleteUser(username: String)

    @Query("SELECT * FROM user_table WHERE login = :username")
    suspend fun getUserDetail(username: String): UserEntity?

}