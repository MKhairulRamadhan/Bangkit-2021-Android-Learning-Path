package com.mkhairulramadhan.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mkhairulramadhan.core.data.local.entity.MovieEntity
import com.mkhairulramadhan.core.data.local.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class GopoxDatabase: RoomDatabase() {

    abstract fun gopoxDao(): GopoxDao
}