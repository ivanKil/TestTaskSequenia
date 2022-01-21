package com.kusch.filmsmvp.presentation.filminfo

import com.kusch.filmsmvp.data.film.Film
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface FilmInfoView : MvpView {
    fun showFilmInfo(film: Film)
    fun setError()
}