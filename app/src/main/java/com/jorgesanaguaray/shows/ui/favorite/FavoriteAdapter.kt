package com.jorgesanaguaray.shows.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jorgesanaguaray.shows.R
import com.jorgesanaguaray.shows.data.local.entity.FavoriteEntity
import com.jorgesanaguaray.shows.databinding.ItemFavoriteBinding

/**
 * Created by Jorge Sanaguaray
 */

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.MyFavoriteViewHolder>() {

    private var shows: List<FavoriteEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFavoriteViewHolder {
        return MyFavoriteViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyFavoriteViewHolder, position: Int) {

        val show = shows[position]

        holder.binding.apply {

            mImage.load(show.image) {
                placeholder(R.drawable.image)
                error(R.drawable.image)
                crossfade(true)
                crossfade(400)
            }

        }

    }

    override fun getItemCount(): Int {
        return shows.size
    }

    class MyFavoriteViewHolder(val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root)

    fun setShows(shows: List<FavoriteEntity>) {
        this.shows = shows
    }

}