package com.jorgesanaguaray.shows.domain.repo

import com.jorgesanaguaray.shows.data.local.FavoriteDao
import com.jorgesanaguaray.shows.data.local.entity.FavoriteEntity
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class DomainRepository @Inject constructor(private val favoriteDao: FavoriteDao) {

    suspend fun insertFavorite(favoriteEntity: FavoriteEntity) {
        favoriteDao.insertFavorite(favoriteEntity)
    }

    suspend fun deleteFavoriteById(id: Int) {
        favoriteDao.deleteFavoriteById(id)
    }

    suspend fun getFavoriteById(id: Int): FavoriteEntity {
        return favoriteDao.getFavoriteById(id)
    }

}