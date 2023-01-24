package com.jorgesanaguaray.shows.data.remote.models

data class ShowModel(

    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Int,
    val averageRuntime: Int,
    val premiered: String,
    val ended: String,
    val officialSite: String,
    val schedule: Schedule,
    val rating: Rating,
    val weight: Int,
    val network: Network,
    val webChannel: WebChannel,
    val dvdCountry: DvdCountry,
    val externals: Externals,
    val image: Image,
    val summary: String,
    val updated: Int,
    val _links: Links

)