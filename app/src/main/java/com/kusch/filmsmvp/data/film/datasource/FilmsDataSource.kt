package com.kusch.filmsmvp.data.film.datasource

import com.kusch.filmsmvp.data.film.FilmsResponse

interface FilmsDataSource {
    suspend fun getFilms(): FilmsResponse
}