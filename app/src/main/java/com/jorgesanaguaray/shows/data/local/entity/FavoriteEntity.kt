package com.jorgesanaguaray.shows.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jorgesanaguaray.shows.data.remote.models.Image

/**
 * Created by Jorge Sanaguaray
 */

@Entity(tableName = "favorite_table")
data class FavoriteEntity(

    @PrimaryKey
    val id: Int,
    val image: String

)
