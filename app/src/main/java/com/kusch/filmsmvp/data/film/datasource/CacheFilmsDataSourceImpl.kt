package com.kusch.filmsmvp.data.film.datasource

import com.kusch.filmsmvp.data.film.Film
import com.kusch.filmsmvp.data.film.Genre
import javax.inject.Inject

class CacheFilmsDataSourceImpl @Inject constructor() : CacheFilmsDataSource {
    private var cachedFilms: List<Film> = listOf()
    private var genres: List<Genre> = listOf()

    override suspend fun getGenres(): List<Genre> {
        return genres
    }

    override suspend fun getFilmById(filmId: Int): Film? {
        return cachedFilms.firstOrNull { it.id == filmId }
    }

    override suspend fun getFilms(genre: String?): List<Film> {
        return if (genre == null)
            cachedFilms
        else
            cachedFilms.filter { it.genres != null }.filter { it.genres!!.contains(genre) }
    }

    override suspend fun retainFilms(films: List<Film>): List<Film> {
        cachedFilms = films.sortedBy { it.localized_name }
        genres = cachedFilms.filter { it.genres != null }
            .flatMap { it.genres!! }
            .distinct()
            .map { Genre(it) }
            .toList()
        return cachedFilms
    }

}