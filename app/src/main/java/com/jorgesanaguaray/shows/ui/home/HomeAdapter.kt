package com.jorgesanaguaray.shows.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jorgesanaguaray.shows.R
import com.jorgesanaguaray.shows.databinding.ItemHomeBinding
import com.jorgesanaguaray.shows.domain.item.ShowItem

/**
 * Created by Jorge Sanaguaray
 */

class HomeAdapter(

    private val homeViewModel: HomeViewModel,
    private val itemPosition: (Int) -> Unit,
    private val onAddClick: (ShowItem) -> Unit

) : RecyclerView.Adapter<HomeAdapter.MyHomeViewHolder>() {

    private var shows: List<ShowItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHomeViewHolder {
        return MyHomeViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHomeViewHolder, position: Int) {

        val show = shows[position]

        holder.binding.apply {

            mImage.load(show.image?.original) {
                placeholder(R.drawable.image)
                error(R.drawable.image)
                crossfade(true)
                crossfade(400)
            }
            mIconAdd.setOnClickListener { itemPosition(position) ; onAddClick(show) }

        }

        setStateOfShow(show.id, holder.binding)

    }

    override fun getItemCount(): Int {
        return shows.size
    }

    class MyHomeViewHolder(val binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root)

    fun setShows(shows: List<ShowItem>) {
        this.shows = shows
    }

    private fun setStateOfShow(id: Int, binding: ItemHomeBinding) {

        if (homeViewModel.isFavorite(id)) {
            binding.mIconAdd.setImageResource(R.drawable.ic_check)
        } else {
            binding.mIconAdd.setImageResource(R.drawable.ic_add)
        }

    }

}