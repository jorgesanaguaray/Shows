package com.jorgesanaguaray.shows.ui.favorite

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.jorgesanaguaray.shows.R
import com.jorgesanaguaray.shows.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        favoriteViewModel = ViewModelProvider(this).get()
        favoriteAdapter = FavoriteAdapter()

    }

    override fun onResume() {
        super.onResume()

        favoriteViewModel.shows.observe(viewLifecycleOwner) {
            favoriteAdapter.setShows(it)
            binding.mRecyclerView.adapter = favoriteAdapter
        }

        favoriteViewModel.recyclerViewVisibility.observe(viewLifecycleOwner) {
            binding.mRecyclerView.visibility = if (it) View.VISIBLE else View.GONE
        }

        favoriteViewModel.floatingButtonVisibility.observe(viewLifecycleOwner) {
            binding.mFloatingButton.visibility = if (it) View.VISIBLE else View.GONE
        }

        favoriteViewModel.noFavoritesVisibility.observe(viewLifecycleOwner) {
            binding.mNoFavorites.visibility = if (it) View.VISIBLE else View.GONE
        }

        favoriteViewModel.progressBarVisibility.observe(viewLifecycleOwner) {
            binding.mProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.mFloatingButton.setOnClickListener { showDeleteDialog() }

        binding.mSwipeRefresh.setOnRefreshListener {
            favoriteViewModel.getFavorites()
            binding.mSwipeRefresh.isRefreshing = false
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDeleteDialog() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setIcon(R.drawable.ic_delete)
        builder.setTitle(R.string.delete_all_favorite_shows)
        builder.setMessage(R.string.are_you_sure)
        builder.setPositiveButton(R.string.yes) { _: DialogInterface, _: Int -> favoriteViewModel.deleteFavorites() }
        builder.setNegativeButton(R.string.no) { _, _ ->}
        builder.setCancelable(false)
        builder.create().show()

    }

}