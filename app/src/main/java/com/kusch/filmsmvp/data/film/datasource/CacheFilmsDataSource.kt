package com.kusch.filmsmvp.data.film.datasource

import com.kusch.filmsmvp.data.film.Film
import com.kusch.filmsmvp.data.film.Genre

interface CacheFilmsDataSource {
    suspend fun getFilms(genre: String?): List<Film>
    suspend fun retainFilms(films: List<Film>): List<Film>
    suspend fun getGenres(): List<Genre>
    suspend fun getFilmById(filmId: Int): Film?
}