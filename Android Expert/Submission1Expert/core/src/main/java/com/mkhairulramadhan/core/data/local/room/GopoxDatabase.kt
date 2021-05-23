package com.mkhairulramadhan.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mkhairulramadhan.core.data.local.entity.MovieEntity
import com.mkhairulramadhan.core.data.local.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class GopoxDatabase: RoomDatabase() {

    abstract fun gopoxDao(): GopoxDao

    companion object{
        private var INSTANCE : GopoxDatabase? = null
        fun getInstance(context: Context): GopoxDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(context.applicationContext, GopoxDatabase::class.java, "GopoxMovie.db").build()
            }
    }
}