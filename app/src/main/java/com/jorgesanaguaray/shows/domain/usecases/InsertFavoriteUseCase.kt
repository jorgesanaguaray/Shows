package com.jorgesanaguaray.shows.domain.usecases

import com.jorgesanaguaray.shows.data.local.entity.FavoriteEntity
import com.jorgesanaguaray.shows.domain.repo.DomainRepository
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class InsertFavoriteUseCase @Inject constructor(private val domainRepository: DomainRepository) {

    suspend operator fun invoke(favoriteEntity: FavoriteEntity) {

        domainRepository.insertFavorite(favoriteEntity)

    }

}