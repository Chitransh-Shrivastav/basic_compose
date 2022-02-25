package com.live.cs007s.jetpack_compose.views.main

import androidx.annotation.WorkerThread
import com.live.cs007s.jetpack_compose.model.Poster
import com.live.cs007s.jetpack_compose.network.RetrofitService
import com.live.cs007s.jetpack_compose.persistence.PosterDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofitService: RetrofitService,
    private val posterDao: PosterDao
) {

  init {
    Timber.d("Injection MainRepository")
  }

  @WorkerThread
  fun loadPosters(
    onStart: () -> Unit,
    onCompletion: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    val posters: List<Poster> = posterDao.getPosterList()
    if (posters.isEmpty()) {
      // request API network call asynchronously.
      retrofitService.fetchPosterList()
        // handle the case when the API request gets a success response.
        .suspendOnSuccess {
          posterDao.insertPosterList(data)
          emit(data)
        }
        // handle the case when the API request gets an error response.
        // e.g. internal server error.
        .onError {
          onError(message())
        }
        // handle the case when the API request gets an exception response.
        // e.g. network connection error.
        .onException {
          onError(message())
        }
    } else {
      emit(posters)
    }
  }.onStart { onStart() }.onCompletion { onCompletion() }.flowOn(Dispatchers.IO)
}
