package com.jorgesanaguaray.shows.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesanaguaray.shows.data.local.entity.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private val _shows = MutableLiveData<List<FavoriteEntity>>()
    val shows: LiveData<List<FavoriteEntity>> get() = _shows

    private val _recyclerViewVisibility = MutableLiveData<Boolean>()
    val recyclerViewVisibility: LiveData<Boolean> get() = _recyclerViewVisibility

    private val _floatingButtonVisibility = MutableLiveData<Boolean>()
    val floatingButtonVisibility: LiveData<Boolean> get() = _floatingButtonVisibility

    private val _noFavoritesVisibility = MutableLiveData<Boolean>()
    val noFavoritesVisibility: LiveData<Boolean> get() = _noFavoritesVisibility

    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> get() = _progressBarVisibility

    init {
        getFavorites()
    }

    fun getFavorites() {

        viewModelScope.launch {

            showProgressBar()

            val shows = favoriteRepository.getFavorites()

            if (shows.isNotEmpty()) {

                _shows.value = shows
                showRecyclerViewAndFloatingButton()

            } else {
                showNoFavorites()
            }

        }

    }

    fun deleteFavorites() {

        viewModelScope.launch {
            favoriteRepository.deleteFavorites()
            showNoFavorites()
        }

    }

    private fun showRecyclerViewAndFloatingButton() {
        _recyclerViewVisibility.value = true
        _floatingButtonVisibility.value = true
        _noFavoritesVisibility.value = false
        _progressBarVisibility.value = false
    }

    private fun showNoFavorites() {
        _recyclerViewVisibility.value = false
        _floatingButtonVisibility.value = false
        _noFavoritesVisibility.value = true
        _progressBarVisibility.value = false
    }

    private fun showProgressBar() {
        _recyclerViewVisibility.value = false
        _floatingButtonVisibility.value = false
        _noFavoritesVisibility.value = false
        _progressBarVisibility.value = true
    }

}