package com.kusch.filmsmvp.presentation.filmlist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kusch.filmsmvp.data.film.ListData

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(dataItem: ListData, position: Int, delegate: FilmAdapter.ActsOnClick?)
}