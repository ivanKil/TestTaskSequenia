package com.kusch.filmsmvp.presentation.filmlist

import com.github.terrakok.cicerone.Router
import com.kusch.filmsmvp.data.film.FilmsRepository
import com.kusch.filmsmvp.presentation.FilmInfoScreen
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class FilmsPresenter @Inject constructor(
    private val filmsRepository: FilmsRepository,
    private val router: Router
) : MvpPresenter<FilmsView>() {

    private var curGenre: String? = null

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewState.setError(throwable)
    }

    override fun onFirstViewAttach() {
        loadData(null)
    }

    private fun loadData(genre: String?) {
        presenterScope.launch(coroutineExceptionHandler) {
            viewState.showFilms(
                curGenre,
                filmsRepository.getFilms(genre),
                filmsRepository.getGenres()
            )
        }
    }

    fun selectGenre(genre: String?) {
        curGenre = if (curGenre.equals(genre)) null else genre
        loadData(curGenre)
    }

    fun clickOnFilm(filmId: Int) {
        router.navigateTo(FilmInfoScreen(filmId))
    }

    fun onError() {
        loadData(curGenre)
    }
}