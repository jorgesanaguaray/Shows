package com.jorgesanaguaray.shows.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
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

            binding.apply {

                mImage.load(it.image?.original) {
                    placeholder(R.drawable.image)
                    error(R.drawable.image)
                    crossfade(true)
                    crossfade(400)
                }

                when (it.genres?.size) {

                    1 -> mGenres.text = HtmlCompat.fromHtml(it.genres[0], HtmlCompat.FROM_HTML_MODE_LEGACY)

                    2 -> mGenres.text = HtmlCompat.fromHtml(it.genres[0] + " • " + it.genres[1], HtmlCompat.FROM_HTML_MODE_LEGACY)

                    3 -> mGenres.text = HtmlCompat.fromHtml(it.genres[0] + " • " + it.genres[1] + " • " + it.genres[2], HtmlCompat.FROM_HTML_MODE_LEGACY)

                    4 -> mGenres.text = HtmlCompat.fromHtml(it.genres[0] + " • " + it.genres[1] + " • " + it.genres[2] + " • " + it.genres[3], HtmlCompat.FROM_HTML_MODE_LEGACY)

                    else -> mGenres.text = " • "

                }

                mButtonOfficialSite.setOnClickListener { _->

                    try {
                        goOfficialSite(it.officialSite!!)
                    } catch (_: Exception) {
                        Toast.makeText(context, R.string.no_official_site, Toast.LENGTH_LONG).show()
                    }

                }

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

    private fun goOfficialSite(officialSite: String) {
        val uri = Uri.parse(officialSite)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}