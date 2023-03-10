package com.jorgesanaguaray.shows.domain.usecases

import com.jorgesanaguaray.shows.data.local.entity.FavoriteEntity
import com.jorgesanaguaray.shows.domain.repo.DomainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class IsFavoriteUseCase @Inject constructor(private val domainRepository: DomainRepository) {

    suspend operator fun invoke(id: Int): Boolean {

        var favoriteEntity: FavoriteEntity?

        runBlocking(Dispatchers.IO) {
            favoriteEntity = domainRepository.getFavoriteById(id)
        }

        if (favoriteEntity == null) return false
        return true

    }

}