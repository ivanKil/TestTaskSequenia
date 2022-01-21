package com.kusch.filmsmvp.data.film

interface FilmsRepository {
    suspend fun getFilms(genre: String?): List<Film>
    suspend fun getGenres(): List<Genre>
    suspend fun getFilmById(filmId: Int): Film?
}