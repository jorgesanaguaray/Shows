package com.jorgesanaguaray.shows.ui.favorite

import com.jorgesanaguaray.shows.data.local.FavoriteDao
import com.jorgesanaguaray.shows.data.local.entity.FavoriteEntity
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {

    suspend fun getFavorites(): List<FavoriteEntity> {

        val shows = favoriteDao.getFavorites()

        return shows.shuffled()

    }

    suspend fun deleteFavorites() {
        favoriteDao.deleteFavorites()
    }

}