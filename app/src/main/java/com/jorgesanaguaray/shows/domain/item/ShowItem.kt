package com.jorgesanaguaray.shows.domain.item

import com.jorgesanaguaray.shows.data.remote.models.*

/**
 * Created by Jorge Sanaguaray
 */

data class ShowItem(

    val id: Int,
    val genres: List<String>?,
    val officialSite: String?,
    val image: Image?

)

fun ShowModel.toShowItem() = ShowItem(id, genres, officialSite, image)
