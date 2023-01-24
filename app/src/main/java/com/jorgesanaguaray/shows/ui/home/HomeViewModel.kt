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

    private val _shows = MutableLiveData<List<ShowItem>>()
    val shows: LiveData<List<ShowItem>> get() = _shows

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getShows()
    }

    fun getShows() {

        viewModelScope.launch {

            try {

                _show.value = homeRepository.getShow()
                _shows.value = homeRepository.getShows()

            } catch (e: Exception) {

                _error.value = e.message

            }

        }

    }

}