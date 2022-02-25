package com.live.cs007s.jetpack_compose.di

import android.app.Application
import androidx.room.Room
import com.live.cs007s.jetpack_compose.R
import com.live.cs007s.jetpack_compose.persistence.AppDatabase
import com.live.cs007s.jetpack_compose.persistence.PosterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideAppDatabase(application: Application): AppDatabase {
    return Room
      .databaseBuilder(
        application,
        AppDatabase::class.java,
        application.getString(R.string.database)
      )
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  @Singleton
  fun providePosterDao(appDatabase: AppDatabase): PosterDao {
    return appDatabase.posterDao()
  }
}
