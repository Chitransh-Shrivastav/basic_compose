package com.live.cs007s.jetpack_compose.views.details

import androidx.annotation.WorkerThread
import com.live.cs007s.jetpack_compose.persistence.PosterDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
  private val posterDao: PosterDao
) {

  @WorkerThread
  fun getPosterById(id: Long) = flow {
    val poster = posterDao.getPoster(id)
    emit(poster)
  }.flowOn(Dispatchers.IO)
}
