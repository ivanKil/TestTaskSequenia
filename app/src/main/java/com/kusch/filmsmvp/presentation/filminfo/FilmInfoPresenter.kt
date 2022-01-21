package com.kusch.filmsmvp.presentation.filminfo

import com.kusch.filmsmvp.data.film.FilmsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class FilmInfoPresenter @AssistedInject constructor(
    @Assisted private val filmId: Int,
    private val filmsRepository: FilmsRepository
) : MvpPresenter<FilmInfoView>() {

    override fun onFirstViewAttach() {
        loadData()
    }

    private fun loadData() {
        presenterScope.launch {
            val film = filmsRepository.getFilmById(filmId)
            if (film != null) viewState.showFilmInfo(film)
            else viewState.setError()
        }
    }
}