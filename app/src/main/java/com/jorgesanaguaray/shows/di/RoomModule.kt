package com.jorgesanaguaray.shows.di

import android.content.Context
import androidx.room.Room
import com.jorgesanaguaray.shows.data.local.ShowsDatabase
import com.jorgesanaguaray.shows.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Jorge Sanaguaray
 */

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShowsDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideFavoriteDao(showsDatabase: ShowsDatabase) = showsDatabase.getFavoriteDao()

}