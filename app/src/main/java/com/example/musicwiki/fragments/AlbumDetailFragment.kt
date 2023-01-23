package com.example.musicwiki.fragments

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicwiki.R
import com.example.musicwiki.adapters.GenreAdapter
import com.example.musicwiki.eventBus.GenreEvent
import com.example.musicwiki.helper.Constants
import com.example.musicwiki.model.Album
import com.example.musicwiki.viewModel.MusicViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.greenrobot.eventbus.EventBus

class AlbumDetailFragment: BaseFragment(), GenreAdapter.IAdapter {
    private val viewModel: MusicViewModel by activityViewModels()
    private var adapter: GenreAdapter?  = null
    private var album: Album? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        album = arguments?.getParcelable(Constants.ALBUM)
    }

    override fun getLayoutRes(): Int {
       return R.layout.fragment_album_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            popBackStack()
        }
        initRecyclerview()
        tvAlbum.text = "Album - ${album?.name}"
        tvArtist.text = "Artist - ${album?.artist?.name}"
        val uri = album?.image?.get(3)?.text
        uri?.let { if(it.isNotEmpty()) Picasso.get().load(it).into(ivAlbumDetail)}
        viewModel._genreInfo.observe(viewLifecycleOwner) {
            tvGenreDesc.text = it?.wiki?.summary
        }
    }

    private fun initRecyclerview() {
        adapter = GenreAdapter(this, 1)
        rvAlbumDetail.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        rvAlbumDetail.adapter = adapter
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