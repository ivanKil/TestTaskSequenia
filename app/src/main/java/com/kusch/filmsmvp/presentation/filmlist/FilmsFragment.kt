package com.kusch.filmsmvp.presentation.filmlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.kusch.filmsmvp.R
import com.kusch.filmsmvp.data.film.Header
import com.kusch.filmsmvp.data.film.ListData
import com.kusch.filmsmvp.data.film.TYPE_FILM
import com.kusch.filmsmvp.databinding.FilmsBinding
import com.kusch.filmsmvp.presentation.abs.AbsFragment
import com.kusch.filmsmvp.presentation.filmlist.adapter.FilmAdapter
import com.kusch.filmsmvp.presentation.utils.IImageLoader
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class FilmsFragment : AbsFragment(R.layout.films), FilmsView, FilmAdapter.ActsOnClick {

    companion object {
        fun newInstance(): Fragment = FilmsFragment()
    }

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    @Inject
    lateinit var presenterProvider: Provider<FilmsPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    private val vb: FilmsBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    private val repoAdapter: FilmAdapter by lazy {
        FilmAdapter(actsOnClick = this, imageLoader)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        initLayoutManager()
        vb.listFilms.adapter = repoAdapter
        return vb.root
    }

    private fun initLayoutManager() {
        val gridLayoutManager = GridLayoutManager(context, 2)
        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (repoAdapter.getItemViewType(position) == TYPE_FILM) 1
                else 2
            }
        }
        vb.listFilms.layoutManager = gridLayoutManager
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun showFilms(genre: String?, films: List<ListData>, genres: List<ListData>) {
        val list = ArrayList<ListData>()
        list.add(Header(resources.getString(R.string.genres)))
        list.addAll(genres)
        list.add(Header(resources.getString(R.string.films)))
        list.addAll(films)
        repoAdapter.selectedGenre = genre
        repoAdapter.submitList(list)
        repoAdapter.notifyDataSetChanged()
    }

    override fun setError(er: Throwable) {
        val snackbar =
            Snackbar.make(
                vb.root,
                requireContext().getString(R.string.err),
                Snackbar.LENGTH_INDEFINITE
            )
        snackbar.setAction(
            requireContext().getString(R.string.repeat)
        ) {
            presenter.onError()
        }
        snackbar.show()
    }

    override fun onFilmPicked(filmId: Int) {
        presenter.clickOnFilm(filmId)
    }

    override fun onGenrePicked(genre: String?) {
        presenter.selectGenre(genre)
    }
}

