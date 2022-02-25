package com.live.cs007s.jetpack_compose.di

import com.live.cs007s.jetpack_compose.network.RetrofitService
import com.live.cs007s.jetpack_compose.persistence.PosterDao
import com.live.cs007s.jetpack_compose.views.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
      retrofitService: RetrofitService,
      posterDao: PosterDao
  ): MainRepository {
    return MainRepository(retrofitService, posterDao)
  }
}
