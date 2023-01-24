package com.jorgesanaguaray.shows.ui.home

import com.jorgesanaguaray.shows.data.remote.ShowService
import com.jorgesanaguaray.shows.domain.item.ShowItem
import com.jorgesanaguaray.shows.domain.item.toShowItem
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class HomeRepository @Inject constructor(private val showService: ShowService) {

    suspend fun getShow(): ShowItem {

        val show = showService.getShows().random()

        return show.toShowItem()

    }

    suspend fun getShows(): List<ShowItem> {

        val shows = showService.getShows().shuffled().take(10)

        return shows.map {
            it.toShowItem()
        }

    }

}