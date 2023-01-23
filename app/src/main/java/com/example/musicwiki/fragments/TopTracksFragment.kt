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
import com.example.musicwiki.model.Artist
import com.example.musicwiki.model.Track
import com.example.musicwiki.viewModel.MusicViewModel
import kotlinx.android.synthetic.main.fragment_top.*

class TopTracksFragment: BaseFragment() {
    private val viewModel: MusicViewModel by activityViewModels()
    private var adapter: TopAdapter?= null
    private var trackList: List<Track>? = listOf()
    override fun getLayoutRes(): Int {
        return R.layout.fragment_top
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressFrame()
        initRecyclerview()
        setObservers()
        viewModel.getGenre()?.let { viewModel.getTop(Constants.getTopTracks, it) }
    }

    private fun setObservers() {
        viewModel._topTracks.observe(viewLifecycleOwner) {
            trackList = it?.track
            adapter?.bindTrackList(trackList)
            hideProgressFrame()
        }
    }

    private fun initRecyclerview() {
        adapter = TopAdapter(Constants.getTopTracks, null, 0)
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
}