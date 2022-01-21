package com.kusch.filmsmvp.presentation.filmlist

import com.kusch.filmsmvp.data.film.ListData
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface FilmsView : MvpView {
    fun showFilms(genre: String?, films: List<ListData>, genres: List<ListData>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setError(er: Throwable)
}