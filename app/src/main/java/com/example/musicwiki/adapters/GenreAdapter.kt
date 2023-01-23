package com.example.musicwiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.musicwiki.R
import com.example.musicwiki.model.Genres
import kotlinx.android.synthetic.main.starting_screen_item_view.view.*
import java.util.*

class GenreAdapter(private val iAdapter: IAdapter?, private var method: Int): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    class GenreViewHolder(view: View): ViewHolder(view)
    private var list: List<Genres>? = listOf()

    fun bindList(list: List<Genres>?) {
        this.list = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return when(method) {
            0 -> {
                val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.starting_screen_item_view, parent, false)
                GenreViewHolder(adapterLayout)
            }
            else -> {
                val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.detail_item_view, parent, false)
                GenreViewHolder(adapterLayout)
            }
        }
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.itemView.apply {
            list?.apply {
                tvGenre.text = this[position].name?.trim()?.capitalize(Locale.ROOT)
                setOnClickListener {
                    this[position].name?.let { it1 -> iAdapter?.onItemClick(it1) }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    interface IAdapter {
        fun onItemClick(genre: String)
    }

}