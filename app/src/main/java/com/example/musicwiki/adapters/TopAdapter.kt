package com.example.musicwiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwiki.R
import com.example.musicwiki.helper.Constants
import com.example.musicwiki.helper.makeGone
import com.example.musicwiki.model.Album
import com.example.musicwiki.model.Artist
import com.example.musicwiki.model.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.top_item_view.view.*

class TopAdapter(private var method: String, private var iListener: IListener?, private var key: Int): RecyclerView.Adapter<TopAdapter.TopViewHolder>() {
    private var list:  List<Album>? = null
    private var artistList: List<Artist>? = null
    private var trackList: List<Track>? = null

    fun bindList(list: List<Album>?) {
        this.list = list
        notifyDataSetChanged()
    }
    fun bindArtistList(list: List<Artist>?) {
        this.artistList = list
        notifyDataSetChanged()
    }
    fun bindTrackList(list: List<Track>?) {
        this.trackList = list
        notifyDataSetChanged()
    }

    class TopViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindAlbums(list:  List<Album>?, iListener: IListener?) {
            this.itemView.apply {
                list?.get(position)?.apply {
                    setOnClickListener {
                        iListener?.onAlbumClick(this)
                    }
                    tvTitle.text = this.name
                    tvArtist.text = this.artist?.name
                    val uri = image?.get(3)?.text
                    uri?.let {if(it.isNotEmpty()) Picasso.get().load(it).into(ivAlbumImage) }
                }
            }
        }

        fun bindArtists(list: List<Artist>?,iListener: IListener?) {
            this.itemView.apply {
                tvAlbumTitle.makeGone()
                list?.get(position)?.apply {
                    setOnClickListener {
                        iListener?.onArtistClick(this)
                    }
                    tvArtist.text = this.name
                    val uri = image?.get(3)?.text
                    uri?.let {if(it.isNotEmpty()) Picasso.get().load(it).into(ivAlbumImage) }
                }
            }
        }

        fun bindTracks(list: List<Track>?, iListener: IListener?) {
            this.itemView.apply {
                list?.get(position)?.apply {
                    setOnClickListener {
                        iListener?.onTrackClick(this)
                    }
                    tvAlbumTitle.text = "Track -"
                    tvTitle.text = this.name
                    tvArtist.text = this.artist?.name
                    val uri = image?.get(3)?.text
                    uri?.let {if(it.isNotEmpty()) Picasso.get().load(it).into(ivAlbumImage) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return when(key) {
            0 -> {
                val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.top_item_view, parent, false)
                TopViewHolder(adapterLayout)
            }
            else -> {
                val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.detail_view, parent, false)
                TopViewHolder(adapterLayout)
            }
        }

    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.itemView.apply {
            when(method) {
                 Constants.getTopAlbums -> holder.bindAlbums(list, iListener)
                 Constants.getTopArtists -> holder.bindArtists(artistList, iListener)
                 Constants.getTopTracks -> holder.bindTracks(trackList, iListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return when(method) {
            Constants.getTopAlbums -> list?.size?:0
            Constants.getTopTracks -> trackList?.size?:0
            else -> artistList?.size?:0
        }
    }
    interface IListener {
        fun onTrackClick(track: Track)
        fun onAlbumClick(album: Album)
        fun onArtistClick(artist: Artist)
    }
}