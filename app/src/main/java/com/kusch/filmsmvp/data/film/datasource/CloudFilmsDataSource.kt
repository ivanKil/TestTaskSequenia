package com.kusch.filmsmvp.data.film.datasource

import com.kusch.filmsmvp.data.api.FilmsApi
import com.kusch.filmsmvp.data.film.FilmsResponse
import javax.inject.Inject

class CloudFilmsDataSource @Inject constructor(private val filmsApi: FilmsApi) : FilmsDataSource {

    override suspend fun getFilms(): FilmsResponse {
        return filmsApi.getFilms()
    }
}