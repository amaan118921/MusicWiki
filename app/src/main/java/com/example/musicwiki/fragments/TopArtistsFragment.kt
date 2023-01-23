package com.example.musicwiki.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.musicwiki.R
import com.example.musicwiki.adapters.TopAdapter
import com.example.musicwiki.helper.Constants
import com.example.musicwiki.helper.makeGone
import com.example.musicwiki.helper.makeVisible
import com.example.musicwiki.model.Album
import com.example.musicwiki.model.Artist
import com.example.musicwiki.model.Track
import com.example.musicwiki.viewModel.MusicViewModel
import kotlinx.android.synthetic.main.fragment_top.*


class TopArtistsFragment: BaseFragment(), TopAdapter.IListener {
    private val viewModel: MusicViewModel by activityViewModels()
    private var adapter: TopAdapter?= null
    private var artistsList: List<Artist>? = listOf()
    override fun getLayoutRes(): Int {
        return R.layout.fragment_top
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressFrame()
        initRecyclerview()
        setObservers()
        viewModel.getGenre()?.let { viewModel.getTop(Constants.getTopArtists, it) }
    }

    private fun setObservers() {
        viewModel._topArtists.observe(viewLifecycleOwner) {
            artistsList = it?.artist
            adapter?.bindArtistList(artistsList)
            hideProgressFrame()
        }
    }

    private fun initRecyclerview() {
        adapter = TopAdapter(Constants.getTopArtists, this, 0)
        rvTop.adapter = adapter
    }


    private fun showProgressFrame() {
        pfTop.makeVisible()
    }

    private fun hideProgressFrame() {
        pfTop.makeGone()
    }


    override fun onClick(p0: View?) {
    }

    override fun onTrackClick(track: Track) {
    }

    override fun onAlbumClick(album: Album) {
    }

    override fun onArtistClick(artist: Artist) {
        toArtistDetailFragment(artist)
    }

    private fun toArtistDetailFragment(artist: Artist) {
        Bundle().apply {
            putParcelable(Constants.ARTIST, artist)
            addFragment(Constants.ARTIST_DETAIL, this)
        }
    }
}