package com.kusch.filmsmvp.data.film

const val TYPE_FILM = 1
const val TYPE_HEADER = 2
const val TYPE_GENRE = 3

data class FilmsResponse(
    val films: List<Film>?,
)

data class Film(
    val id: Int,
    val localized_name: String?,
    val name: String?,
    val year: Int?,
    val rating: String?,
    val image_url: String?,
    val description: String?,
    val genres: List<String>?,
) : ListData {
    override fun getType() = TYPE_FILM
}

data class Header(val text: String) : ListData {
    override fun getType() = TYPE_HEADER
}

class Genre(val text: String) : ListData {
    override fun getType() = TYPE_GENRE
}

interface ListData {
    fun getType(): Int
}