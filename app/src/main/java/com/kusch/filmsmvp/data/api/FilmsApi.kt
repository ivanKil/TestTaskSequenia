package com.kusch.filmsmvp.data.api

import com.kusch.filmsmvp.data.film.FilmsResponse
import retrofit2.http.GET

interface FilmsApi {
    @GET("films.json")
    suspend fun getFilms(): FilmsResponse
}