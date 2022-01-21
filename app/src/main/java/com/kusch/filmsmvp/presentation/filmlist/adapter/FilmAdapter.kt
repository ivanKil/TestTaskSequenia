package com.kusch.filmsmvp.presentation.filmlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kusch.filmsmvp.R
import com.kusch.filmsmvp.data.film.*
import com.kusch.filmsmvp.databinding.ItemFilmBinding
import com.kusch.filmsmvp.databinding.ItemGenreBinding
import com.kusch.filmsmvp.databinding.ItemHeaderBinding
import com.kusch.filmsmvp.presentation.utils.IImageLoader

class FilmAdapter(
    private val actsOnClick: ActsOnClick?,
    private val imageLoader: IImageLoader<ImageView>
) :
    ListAdapter<ListData, BaseViewHolder>(FilmDiff) {

    var selectedGenre: String? = null

    interface ActsOnClick {
        fun onFilmPicked(filmId: Int)
        fun onGenrePicked(genre: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            TYPE_FILM ->
                FilmViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_film, parent, false), imageLoader
                )
            TYPE_GENRE ->
                GenreViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_genre, parent, false)
                )
            else ->
                HeaderViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_header, parent, false)
                )
        }

    inner class GenreViewHolder(view: View) : BaseViewHolder(view) {

        private val viewBinding: ItemGenreBinding by viewBinding()

        override fun bind(genre: ListData, position: Int, delegate: ActsOnClick?) {
            with(viewBinding) {
                title.text = (genre as Genre).text

                cardGenre.setBackgroundColor(
                    if (genre.text == selectedGenre) {
                        ContextCompat.getColor(root.context, R.color.selected_film)
                    } else ContextCompat.getColor(root.context, R.color.black)
                )
                cardGenre.setOnClickListener { delegate?.onGenrePicked(genre.text) }
            }
        }
    }

    inner class FilmViewHolder(view: View, private val imageLoader: IImageLoader<ImageView>) :
        BaseViewHolder(view) {

        private val viewBinding: ItemFilmBinding by viewBinding()

        override fun bind(film: ListData, position: Int, delegate: ActsOnClick?) {
            with(viewBinding) {
                title.text = (film as Film).localized_name
                cardFilm.setOnClickListener { delegate?.onFilmPicked(film.id) }
                imageLoader.loadInto(film.image_url ?: "", poster)
            }
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {

        private val viewBinding: ItemHeaderBinding by viewBinding()

        override fun bind(header: ListData, position: Int, delegate: ActsOnClick?) {
            with(viewBinding) {
                title.text = (header as Header).text
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        holder.bind(getItem(position), position, actsOnClick)

    override fun getItemViewType(position: Int): Int {
        return currentList[position].getType()
    }
}