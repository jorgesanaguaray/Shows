package com.jorgesanaguaray.shows.data.remote

import com.jorgesanaguaray.shows.data.remote.models.ShowModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class ShowService @Inject constructor(private val showApi: ShowApi) {

    suspend fun getShows(): List<ShowModel> {

        return withContext(Dispatchers.IO) {
            val shows = showApi.getShows()
            shows.body() ?: emptyList()
        }

    }

}