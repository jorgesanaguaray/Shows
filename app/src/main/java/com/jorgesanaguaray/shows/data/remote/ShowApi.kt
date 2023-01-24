package com.jorgesanaguaray.shows.data.remote

import com.jorgesanaguaray.shows.data.remote.models.ShowModel
import com.jorgesanaguaray.shows.util.Constants.Companion.SHOWS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Jorge Sanaguaray
 */

interface ShowApi {

    @GET(SHOWS_ENDPOINT)
    suspend fun getShows(): Response<List<ShowModel>>

}