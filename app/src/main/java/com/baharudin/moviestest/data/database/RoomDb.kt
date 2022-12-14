package com.baharudin.moviestest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baharudin.moviestest.BuildConfig
import com.baharudin.moviestest.data.database.dao.MovieDao
import com.baharudin.moviestest.data.database.dao.TvShowDao
import com.baharudin.moviestest.data.database.model.MovieEntity
import com.baharudin.moviestest.data.database.model.TvShowEntity

@Database(
    entities = [
        MovieEntity::class,
        TvShowEntity::class
    ],
    version = BuildConfig.VERSION_CODE,
    //exportSchema = false
)

abstract class RoomDb : RoomDatabase() {

    abstract fun movie() : MovieDao
    abstract fun tvShow() : TvShowDao

    companion object {

        @Volatile private var instance : RoomDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RoomDb::class.java,
            BuildConfig.APPLICATION_ID
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}