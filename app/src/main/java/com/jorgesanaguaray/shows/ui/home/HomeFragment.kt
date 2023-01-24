package com.jorgesanaguaray.shows.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        homeViewModel = ViewModelProvider(this).get()
        homeAdapter = HomeAdapter()

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

        homeViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        homeViewModel.getShows()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}