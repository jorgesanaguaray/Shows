package com.jorgesanaguaray.shows.domain.item

import com.jorgesanaguaray.shows.data.remote.models.*

/**
 * Created by Jorge Sanaguaray
 */

data class ShowItem(

    val id: Int,
    val url: String?,
    val name: String?,
    val type: String?,
    val language: String?,
    val genres: List<String>?,
    val status: String?,
    val runtime: Int?,
    val averageRuntime: Int?,
    val premiered: String?,
    val ended: String?,
    val officialSite: String?,
    val schedule: Schedule?,
    val rating: Rating?,
    val weight: Int?,
    val network: Network?,
    val webChannel: WebChannel?,
    val dvdCountry: DvdCountry?,
    val externals: Externals?,
    val image: Image?,
    val summary: String?,
    val updated: Int?,
    val _links: Links?

)

fun ShowModel.toShowItem() = ShowItem(id, url, name, type, language, genres, status, runtime, averageRuntime, premiered, ended, officialSite, schedule, rating, weight, network, webChannel, dvdCountry, externals, image, summary, updated, _links)
