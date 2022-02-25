package com.live.cs007s.jetpack_compose.views.main

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.live.cs007s.jetpack_compose.model.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  mainRepository: MainRepository
) : ViewModel() {

  val posterList: Flow<List<Poster>> =
    mainRepository.loadPosters(
      onStart = { _isLoading.value = true },
      onCompletion = { _isLoading.value = false },
      onError = { Timber.d(it) }
    )

  private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
  val isLoading: State<Boolean> get() = _isLoading

  private val _selectedTab: MutableState<Int> = mutableStateOf(0)
  val selectedTab: State<Int> get() = _selectedTab

  init {
    Timber.d("injection MainViewModel")
  }

  fun selectTab(@StringRes tab: Int) {
    _selectedTab.value = tab
  }
}
