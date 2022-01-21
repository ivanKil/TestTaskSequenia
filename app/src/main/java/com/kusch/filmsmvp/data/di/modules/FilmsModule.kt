package com.kusch.filmsmvp.data.di.modules

import android.widget.ImageView
import com.kusch.filmsmvp.data.film.FilmsRepository
import com.kusch.filmsmvp.data.film.FilmsRepositoryImpl
import com.kusch.filmsmvp.data.film.datasource.CacheFilmsDataSource
import com.kusch.filmsmvp.data.film.datasource.CacheFilmsDataSourceImpl
import com.kusch.filmsmvp.data.film.datasource.CloudFilmsDataSource
import com.kusch.filmsmvp.data.film.datasource.FilmsDataSource
import com.kusch.filmsmvp.presentation.MainActivity
import com.kusch.filmsmvp.presentation.filminfo.FilmInfoFragment
import com.kusch.filmsmvp.presentation.filmlist.FilmsFragment
import com.kusch.filmsmvp.presentation.utils.GlideImageLoader
import com.kusch.filmsmvp.presentation.utils.IImageLoader
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module

interface FilmsModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindFilmsFragment(): FilmsFragment

    @ContributesAndroidInjector
    fun bindFilmInfoFragment(): FilmInfoFragment

    @Singleton
    @Binds
    fun bindFilmsRepository(repository: FilmsRepositoryImpl): FilmsRepository

    @Reusable
    @Binds
    fun bindFilmsDataSource(dataSource: CloudFilmsDataSource): FilmsDataSource

    @Reusable
    @Binds
    fun bindCacheFilmsDataSource(dataSource: CacheFilmsDataSourceImpl): CacheFilmsDataSource

    @Singleton
    @Binds
    fun bindImageLoader(glideImageLoader: GlideImageLoader): IImageLoader<ImageView>
}