package com.jorgesanaguaray.shows.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import coil.load
import com.jorgesanaguaray.shows.R
import com.jorgesanaguaray.shows.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapterA: HomeAdapter
    private lateinit var homeAdapterB: HomeAdapter
    private lateinit var homeAdapterC: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        homeViewModel = ViewModelProvider(this).get()
        homeAdapterA = HomeAdapter()
        homeAdapterB = HomeAdapter()
        homeAdapterC = HomeAdapter()

    }

    override fun onResume() {
        super.onResume()

        homeViewModel.show.observe(viewLifecycleOwner) {

            binding.mImage.load(it.image?.original) {
                placeholder(R.drawable.image)
                error(R.drawable.image)
                crossfade(true)
                crossfade(400)
            }

        }

        homeViewModel.showsA.observe(viewLifecycleOwner) {
            homeAdapterA.setShows(it)
            binding.mRecyclerViewA.adapter = homeAdapterA
        }

        homeViewModel.showsB.observe(viewLifecycleOwner) {
            homeAdapterB.setShows(it)
            binding.mRecyclerViewB.adapter = homeAdapterB
        }

        homeViewModel.showsC.observe(viewLifecycleOwner) {
            homeAdapterC.setShows(it)
            binding.mRecyclerViewC.adapter = homeAdapterC
        }

        homeViewModel.error.observe(viewLifecycleOwner) {
            binding.mError.text = it
        }

        homeViewModel.nestedScrollVisibility.observe(viewLifecycleOwner) {
            binding.mNestedScroll.visibility = if (it) View.VISIBLE else View.GONE
        }

        homeViewModel.cardErrorVisibility.observe(viewLifecycleOwner) {
            binding.mCardError.visibility = if (it) View.VISIBLE else View.GONE
        }

        homeViewModel.progressBarVisibility.observe(viewLifecycleOwner) {
            binding.mProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.mSwipeRefresh.setOnRefreshListener {
            homeViewModel.getShows()
            binding.mSwipeRefresh.isRefreshing = false
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}