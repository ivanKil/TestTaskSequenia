package com.kusch.filmsmvp.presentation.filminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kusch.filmsmvp.R
import com.kusch.filmsmvp.data.film.Film
import com.kusch.filmsmvp.databinding.FilmInfoBinding
import com.kusch.filmsmvp.presentation.abs.AbsFragment
import com.kusch.filmsmvp.presentation.utils.IImageLoader
import moxy.ktx.moxyPresenter
import javax.inject.Inject


const val EXT_FILM_ID = "EXT_FILM_ID"

class FilmInfoFragment : AbsFragment(R.layout.film_info), FilmInfoView {

    companion object {
        fun newInstance(filmId: Int): Fragment = FilmInfoFragment().apply {
            arguments = bundleOf(Pair(EXT_FILM_ID, filmId))
        }
    }

    @Inject
    lateinit var filmInfoPresenterFactory: FilmInfoPresenterFactory

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    private val presenter: FilmInfoPresenter by moxyPresenter {
        filmInfoPresenterFactory.create(requireArguments().getInt(EXT_FILM_ID))
    }

    private val vb: FilmInfoBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return vb.root
    }

    override fun showFilmInfo(film: Film) {
        vb.apply {
            infoNameLocalized.text = film.localized_name
            infoName.text = film.name
            imageLoader.loadInto(film.image_url ?: "", infoPoster)
            infoYear.text = String.format(
                resources.getString(R.string.year),
                if (film.year == null) resources.getString(R.string.dont_know)
                else film.year.toString()
            )
            infoRating.text = String.format(
                resources.getString(R.string.rating),
                if (film.year == null) resources.getString(R.string.dont_know)
                else film.year.toString()
            )
            infoDescription.text = film.description
        }
    }

    override fun setError() {
        Toast.makeText(
            requireActivity(), requireContext().getString(R.string.err),
            Toast.LENGTH_LONG
        ).show()
    }
}

