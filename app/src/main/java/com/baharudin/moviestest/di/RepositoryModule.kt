package com.baharudin.moviestest.di

import androidx.paging.PagedList
import com.baharudin.moviestest.data.database.RoomDb
import com.baharudin.moviestest.data.factory.Factory
import com.baharudin.moviestest.data.network.ApiService
import com.baharudin.moviestest.data.repository.DataRepository
import com.baharudin.moviestest.data.repository.local.LocalRepository
import com.baharudin.moviestest.data.repository.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRemoteRepository(
        apiService: ApiService,
        config : PagedList.Config,
        factory : Factory
    ) : RemoteRepository = RemoteRepository(
        apiService,
        config,
        factory
    )

    @Singleton
    @Provides
    fun provideLocalRepository(
        database : RoomDb,
        config : PagedList.Config
    ) : LocalRepository = LocalRepository(database, config)


    @Singleton
    @Provides
    fun provideDataRepository(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ) : DataRepository = DataRepository(
        remoteRepository,
        localRepository
    )
}