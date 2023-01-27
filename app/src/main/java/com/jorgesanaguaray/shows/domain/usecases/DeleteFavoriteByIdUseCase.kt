package com.jorgesanaguaray.shows.domain.usecases

import com.jorgesanaguaray.shows.domain.repo.DomainRepository
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class DeleteFavoriteByIdUseCase @Inject constructor(private val domainRepository: DomainRepository) {

    suspend operator fun invoke(id: Int) {

        domainRepository.deleteFavoriteById(id)

    }

}