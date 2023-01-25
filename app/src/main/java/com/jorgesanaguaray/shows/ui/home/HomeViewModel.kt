package com.jorgesanaguaray.shows.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesanaguaray.shows.domain.item.ShowItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _show = MutableLiveData<ShowItem>()
    val show: LiveData<ShowItem> get() = _show

    private val _showsA = MutableLiveData<List<ShowItem>>()
    val showsA: LiveData<List<ShowItem>> get() = _showsA

    private val _showsB = MutableLiveData<List<ShowItem>>()
    val showsB: LiveData<List<ShowItem>> get() = _showsB

    private val _showsC = MutableLiveData<List<ShowItem>>()
    val showsC: LiveData<List<ShowItem>> get() = _showsC

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _nestedScrollVisibility = MutableLiveData<Boolean>()
    val nestedScrollVisibility: LiveData<Boolean> get() = _nestedScrollVisibility

    private val _cardErrorVisibility = MutableLiveData<Boolean>()
    val cardErrorVisibility: LiveData<Boolean> get() = _cardErrorVisibility

    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> get() = _progressBarVisibility

    init {
        getShows()
    }

    fun getShows() {

        viewModelScope.launch {

            showProgressBar()

            try {

                _show.value = homeRepository.getShow()
                _showsA.value = homeRepository.getShows()
                _showsB.value = homeRepository.getShows()
                _showsC.value = homeRepository.getShows()
                showNestedScroll()

            } catch (e: Exception) {

                _error.value = e.message
                showCardError()

            }

        }

    }

    private fun showNestedScroll() {
        _nestedScrollVisibility.value = true
        _cardErrorVisibility.value = false
        _progressBarVisibility.value = false
    }

    private fun showCardError() {
        _nestedScrollVisibility.value = false
        _cardErrorVisibility.value = true
        _progressBarVisibility.value = false
    }

    private fun showProgressBar() {
        _nestedScrollVisibility.value = false
        _cardErrorVisibility.value = false
        _progressBarVisibility.value = true
    }

}