package com.live.cs007s.jetpack_compose.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.live.cs007s.jetpack_compose.model.Poster

@Database(entities = [Poster::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

  abstract fun posterDao(): PosterDao
}
