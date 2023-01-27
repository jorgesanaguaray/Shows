package com.jorgesanaguaray.shows.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jorgesanaguaray.shows.data.local.entity.FavoriteEntity

/**
 * Created by Jorge Sanaguaray
 */

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class ShowsDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

}