package com.kusch.filmsmvp.data.film

import com.kusch.filmsmvp.data.film.datasource.CacheFilmsDataSource
import com.kusch.filmsmvp.data.film.datasource.FilmsDataSource
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val cloud: FilmsDataSource,
    private val cache: CacheFilmsDataSource,
) : FilmsRepository {

    override suspend fun getFilms(genre: String?): List<Film> {
        return if (cache.getFilms(genre).isEmpty()) {
            cache.retainFilms(cloud.getFilms().films ?: listOf())
        } else
            cache.getFilms(genre)
    }

    override suspend fun getGenres(): List<Genre> {
        return cache.getGenres()
    }

    override suspend fun getFilmById(filmId: Int): Film? {
        if (cache.getFilms(null).isEmpty()) {
            cache.retainFilms(cloud.getFilms().films ?: listOf())
        }
        return cache.getFilmById(filmId)
    }

}