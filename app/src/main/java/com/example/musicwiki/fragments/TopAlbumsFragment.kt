package com.example.musicwiki.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.musicwiki.R
import com.example.musicwiki.adapters.TopAdapter
import com.example.musicwiki.fragments.BaseFragment
import com.example.musicwiki.helper.Constants
import com.example.musicwiki.helper.makeGone
import com.example.musicwiki.helper.makeVisible
import com.example.musicwiki.model.Album
import com.example.musicwiki.model.AlbumModel
import com.example.musicwiki.model.Artist
import com.example.musicwiki.model.Track
import com.example.musicwiki.viewModel.MusicViewModel
import kotlinx.android.synthetic.main.fragment_top.*

class TopAlbumsFragment: BaseFragment(), TopAdapter.IListener {
    private val viewModel: MusicViewModel by activityViewModels()
    private var adapter: TopAdapter?= null
    private var albumList: List<Album>? = null
    override fun getLayoutRes(): Int {
        return R.layout.fragment_top
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressFrame()
        initRecyclerview()
        setObservers()
        viewModel.getGenre()?.let { viewModel.getTop(Constants.getTopAlbums, it) }
    }

    private fun setObservers() {
        viewModel._top.observe(viewLifecycleOwner) {
            albumList = it?.album
            adapter?.bindList(albumList)
            hideProgressFrame()
        }
    }

    private fun showProgressFrame() {
        pfTop.makeVisible()
    }

    private fun hideProgressFrame() {
        pfTop.makeGone()
    }

    private fun initRecyclerview() {
        adapter = TopAdapter(Constants.getTopAlbums, this, 0)
        rvTop.adapter = adapter
    }

    override fun onClick(p0: View?) {

    }

    override fun onTrackClick(track: Track) {
    }

    override fun onAlbumClick(album: Album) {
        toAlbumDetail(album)
    }

    private fun toAlbumDetail(album: Album) {
        Bundle().apply {
            putParcelable(Constants.ALBUM, album)
            addFragment(Constants.ALBUM_DETAIL, this)
        }
    }

    override fun onArtistClick(artist: Artist) {
    }
}