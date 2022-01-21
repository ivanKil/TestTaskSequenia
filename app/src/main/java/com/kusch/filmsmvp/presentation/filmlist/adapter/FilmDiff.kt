package com.kusch.filmsmvp.presentation.filmlist.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.kusch.filmsmvp.data.film.Film
import com.kusch.filmsmvp.data.film.Genre
import com.kusch.filmsmvp.data.film.Header
import com.kusch.filmsmvp.data.film.ListData

object FilmDiff : DiffUtil.ItemCallback<ListData>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: ListData, newItem: ListData): Boolean {
        return if (oldItem is Film && newItem is Film)
            oldItem.id == newItem.id
        else if (oldItem is Header && newItem is Header)
            oldItem.text == newItem.text
        else if (oldItem is Genre && newItem is Genre)
            oldItem.text == newItem.text
        else
            false
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ListData, newItem: ListData): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ListData, newItem: ListData) = payload

}

