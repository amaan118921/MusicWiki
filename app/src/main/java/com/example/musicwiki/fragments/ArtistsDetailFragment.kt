package com.example.musicwiki.fragments

import android.media.metrics.Event
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicwiki.R
import com.example.musicwiki.adapters.GenreAdapter
import com.example.musicwiki.adapters.TopAdapter
import com.example.musicwiki.eventBus.GenreEvent
import com.example.musicwiki.eventBus.MessageEvent
import com.example.musicwiki.helper.Constants
import com.example.musicwiki.helper.makeGone
import com.example.musicwiki.helper.makeVisible
import com.example.musicwiki.model.Album
import com.example.musicwiki.model.Artist
import com.example.musicwiki.model.Stats
import com.example.musicwiki.model.Track
import com.example.musicwiki.viewModel.MusicViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*
import kotlinx.android.synthetic.main.fragment_album_detail.tvArtist
import kotlinx.android.synthetic.main.fragment_artist_detail.*
import kotlinx.android.synthetic.main.fragment_top.*
import org.greenrobot.eventbus.EventBus

class ArtistsDetailFragment: BaseFragment(), GenreAdapter.IAdapter {
    private val viewModel: MusicViewModel by activityViewModels()
    private var artist: Artist? = null
    private var adapter: GenreAdapter? = null
    private  var artistStats: Stats? = null
    private var topAdapter: TopAdapter? = null
    private var trackAdapter: TopAdapter? = null
    private var albumsList: List<Album>? = null
    private var tracksList: List<Track>? = null
    override fun getLayoutRes(): Int {
        return R.layout.fragment_artist_detail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artist = arguments?.getParcelable(Constants.ARTIST)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            reset()
            popBackStack()
        }
        showProgressFrame()
        initRecyclerview()
        initTrackRV()
        initAlbumRV()
        tvArtist.text = "Artist - ${artist?.name}"
        val uri = artist?.image?.get(3)?.text
        uri?.let { if(it.isNotEmpty()) Picasso.get().load(it).into(ivArtistDetail)}
        setListeners()
        artist?.name?.let { viewModel.getArtistInfo(Constants.getArtistInfo, it) }
        artist?.name?.let { viewModel.getTopAlbums(Constants.getArtistTopAlbums, it) }
        artist?.name?.let { viewModel.getTopTracks(Constants.getArtistTopTracks, it) }
    }

    private fun initTrackRV() {
        trackAdapter = TopAdapter(Constants.getTopTracks, null, 1)
        rvTopTracks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvTopTracks.adapter = trackAdapter
    }

    private fun initAlbumRV() {
        topAdapter = TopAdapter(Constants.getTopAlbums, null, 1)
        rvTopAlbums.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvTopAlbums.adapter = topAdapter
    }

    private fun showProgressFrame() {
        pfArtist.makeVisible()
    }

    private fun hideProgressFrame() {
        pfArtist.makeGone()
    }

    private fun reset() {
        viewModel._artistInfo.value=null
    }

    private fun setListeners() {
        viewModel._artistInfo.observe(viewLifecycleOwner) {
            artistStats = it
            update()
        }
        viewModel._artistTopAlbums.observe(viewLifecycleOwner) {
            albumsList = it
            topAdapter?.bindList(albumsList)
        }
        viewModel._artistTopTracks.observe(viewLifecycleOwner) {
            tracksList = it
            trackAdapter?.bindTrackList(tracksList)
        }
    }

    private fun update() {
        if(artistStats!=null) {
            tvPlayCount.text = "Playcount - ${artistStats?.playcount}"
            tvFollowers.text = "Listeners - ${artistStats?.listeners}"
            hideProgressFrame()
        }
    }

    private fun initRecyclerview() {
        adapter = GenreAdapter(this, 1)
        rvArtistDetail.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)
        rvArtistDetail.adapter = adapter
        viewModel.genres.observe(viewLifecycleOwner) {
            adapter?.bindList(it)
        }
    }
    override fun onClick(v: View?) {

    }

    override fun onItemClick(genre: String) {
        popBackStack()
        popBackStack()
        EventBus.getDefault().post(GenreEvent(genre, Constants.ARTIST_DETAIL))
    }

}